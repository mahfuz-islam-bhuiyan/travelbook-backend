package org.travelbook.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.travelbook.backend.dao.domain.TravelBookApiResponse;
import org.travelbook.backend.dao.domain.User;
import org.travelbook.backend.service.UserService;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

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
