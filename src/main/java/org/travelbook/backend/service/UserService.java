package org.travelbook.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.travelbook.backend.dao.configs.Messages;
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

    public TravelBookApiResponse create(User user) {
        TravelBookApiResponse apiResponse = new TravelBookApiResponse(TravelBookApiResponse.SUCCESS_STATUS);

        if (isDuplicateEmail(user.getEmail()))
            return apiResponse.getErrorApiResponse(log, Messages.Error.USER_CREATE_DUPLICATE_EMAIL, null);

        try {
            userMapper.create(user);
        } catch (TravelBookException e) {
            return apiResponse.getErrorApiResponse(log, Messages.Error.INVALID_INPUT, e);
        }

        if (user.getUserId() != null && user.getUserId() > 0)
            apiResponse.msg = Messages.Success.USER_CREATE_SUCCESSFUL;

        return apiResponse;
    }

    public ArrayList<User> getAll() throws TravelBookException {
        return userMapper.getAll();
    }

    public boolean isDuplicateEmail(String email) {
        if (ValidationUtil.isNotValidEmail(email)) return true;

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
}
