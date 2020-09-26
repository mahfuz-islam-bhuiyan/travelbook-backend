package org.travelbook.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.travelbook.backend.dao.domain.TravelBookApiResponse;
import org.travelbook.backend.dao.domain.User;
import org.travelbook.backend.security.jwt.JwtAuthenticationService;
import org.travelbook.backend.security.jwt.JwtRequest;
import org.travelbook.backend.service.UserService;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationTokenForPWDJp(@RequestBody JwtRequest authenticationRequest) throws Exception {
        Map<String, Object> result = this.jwtAuthenticationService.createAuthenticationTokenForUser(authenticationRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = {"/"})
    public ArrayList<User> getUser() {
        try {
            return userService.getAll();
        } catch (TravelBookException e) {
            log.error("error fetching user list", e);
            return new ArrayList<>();
        }
    }

    @PostMapping(value = {"/"})
    public TravelBookApiResponse createUser(@RequestBody User User) {
        return userService.create(User);
    }
}
