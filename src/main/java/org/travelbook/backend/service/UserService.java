package org.travelbook.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travelbook.backend.security.jwt.JwtAuthenticationService;
import org.travelbook.backend.security.jwt.JwtRequest;
import org.travelbook.backend.utils.Messages;
import org.travelbook.backend.dao.domain.TravelBookApiResponse;
import org.travelbook.backend.dao.domain.User;
import org.travelbook.backend.dao.persistence.UserMapper;
import org.travelbook.backend.utils.TravelBookException;
import org.travelbook.backend.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @Transactional
    public TravelBookApiResponse create(User user) {
        TravelBookApiResponse apiResponse = new TravelBookApiResponse(TravelBookApiResponse.SUCCESS_STATUS);

        if (user == null || StringUtils.isEmpty(user.getEmail())
                || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getName()))
            return apiResponse.getErrorApiResponse(log, Messages.Error.INVALID_INPUT, null);

        if (isDuplicateEmail(user.getEmail()))
            return apiResponse.getErrorApiResponse(log, Messages.Error.USER_CREATE_DUPLICATE_EMAIL, null);

        // encoding the password
        String passwordWithoutEncryption = user.getPassword();
        user.setPassword(bcryptEncoder.encode(passwordWithoutEncryption));

        try {
            userMapper.create(user);
        } catch (TravelBookException e) {
            return apiResponse.getErrorApiResponse(log, Messages.Error.INVALID_INPUT, e);
        }

        if (user.getUserId() != null && user.getUserId() > 0) {
            apiResponse.msg = Messages.Success.USER_CREATE_SUCCESSFUL;

            Map<String, Object> userInfoMap = new HashMap<>();
            JwtRequest jwtRequest = new JwtRequest(user.getEmail(), passwordWithoutEncryption);
            try {
                userInfoMap = jwtAuthenticationService.createAuthenticationTokenForUser(jwtRequest);
            } catch (Exception e) {
                return apiResponse.getErrorApiResponse(log, Messages.Error.ERROR_AUTH_AFTER_SIGN_UP, null);
            }

            if (userInfoMap != null) {
                apiResponse.result = userInfoMap;
            }
        }

        return apiResponse;
    }

    public ArrayList<User> getAll() throws TravelBookException {
        return userMapper.getAll();
    }

    public boolean isDuplicateEmail(String email) {
        Map<String, Object> param = new HashMap<>();
        param.put("email", email);
        try {
            return userMapper.isDuplicateEmail(param);
        } catch (TravelBookException e) {
            return true;
        }
    }

    public User getByParam(Map<String, Object> param) throws TravelBookException {
        if (param != null)
            return userMapper.getByParam(param);
        return null;
    }

    public User getByEmail(String email) throws TravelBookException {
        if (StringUtils.isEmpty(email)) return null;

        Map<String, Object> param = new HashMap<>();
        param.put("email", email);
        return getByParam(param);
    }

    public User getUserForAuth(String email) throws TravelBookException {
        return StringUtils.isEmpty(email) ? null : userMapper.getUserForAuth(email);
    }
}
