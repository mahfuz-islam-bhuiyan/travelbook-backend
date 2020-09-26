package org.travelbook.backend.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    private JwtUserDetailsService userDetailsService;


    public Map<String, Object> createAuthenticationTokenForUser(@RequestBody JwtRequest authenticationRequest) throws Exception {
        return this.doAuthentication(authenticationRequest);
    }

    private Map<String, Object> doAuthentication(JwtRequest authenticationRequest) throws Exception {

        final TravelBookUserDetails userDetails = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if (userDetails == null || userDetails.getUser() == null) {
            throw new Exception("INVALID_CREDENTIALS", new BadCredentialsException("Unauthorized"));
        }

        final String token = jwtTokenUtil.generateToken(userDetails);

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("user", userDetails.getUser());

        return result;
    }

    private TravelBookUserDetails authenticate(String username, String password) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return (authentication != null && authentication.getPrincipal() != null) ? (TravelBookUserDetails) authentication.getPrincipal() : null;
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
