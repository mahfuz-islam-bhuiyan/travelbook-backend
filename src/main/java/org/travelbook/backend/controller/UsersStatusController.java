package org.travelbook.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.travelbook.backend.dao.domain.TravelBookApiResponse;
import org.travelbook.backend.dao.domain.UsersStatus;
import org.travelbook.backend.service.UsersStatusService;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping(value = "status")
public class UsersStatusController {

    @Autowired
    private UsersStatusService usersStatusService;

    @GetMapping(value = {"/"})
    public ArrayList<UsersStatus> getStatuses() {
        try {
            return usersStatusService.getAll();
        } catch (TravelBookException e) {
            log.error("error fetching user list", e);
            return new ArrayList<>();
        }
    }

    @PostMapping(value = {"/"})
    public TravelBookApiResponse create(@RequestBody UsersStatus usersStatus) {
        return usersStatusService.create(usersStatus);
    }
}
