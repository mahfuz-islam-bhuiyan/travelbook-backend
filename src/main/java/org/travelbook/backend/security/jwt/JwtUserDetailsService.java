package org.travelbook.backend.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.travelbook.backend.dao.domain.User;
import org.travelbook.backend.service.UserService;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsService.class);

    private static final String errMsg1 = "User not found with email: ";
    private static final String errMsg2 = "Invalid email entered";

    @Override
    public TravelBookUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            String email = username;
//            Map<String, Object> objectMap = new HashMap<>();
//            objectMap.put("email", email);
            user = userService.getUserForAuth(email);
        } catch (TravelBookException e) {
            LOGGER.error(errMsg1 + username, e);
            throw new BadCredentialsException(errMsg1 + username);
        }

        if (user == null || user.getUserId() == null || user.getUserId().equals(0))
            throw new BadCredentialsException(errMsg2);

        List<GrantedAuthority> grantedAuths = new ArrayList<>();

//        try {
//            List<EpwdUserRole> rolesPermissions = userService.getPermissionByUserId(user.getUserId());
//
//            if (CollectionUtils.isNotEmpty(rolesPermissions)) {
//                grantedAuths.addAll(rolesPermissions.stream()
//                        .map(role -> new SimpleGrantedAuthority(role.getPermissionName())).collect(Collectors.toList()));
//            } else {
//                throw new BadCredentialsException("No Permission is assigned to this user");
//            }
//
//        } catch (TravelBookException e) {
//            throw new BadCredentialsException("Error while loading user permission");
//        }

        TravelBookUserDetails epwdUserDetails = new TravelBookUserDetails(username, user.getPassword(), grantedAuths);
        user.setPassword("");

        /*
            Set permissions to User for future reference from front-end app
         */

//        Iterator<GrantedAuthority> it = grantedAuths.iterator();
//        Map<String, Boolean> perm = new HashMap<>();
//        while (it.hasNext()) {
//            String key = it.next().toString();
//            perm.put(key, true);
//        }
//        user.setPermissions(perm);

        epwdUserDetails.setUser(user);
        return epwdUserDetails;
    }
}
