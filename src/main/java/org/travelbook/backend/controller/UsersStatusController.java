package org.travelbook.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.travelbook.backend.dao.domain.TravelBookApiResponse;
import org.travelbook.backend.dao.domain.UsersStatus;
import org.travelbook.backend.service.UsersStatusService;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "status")
public class UsersStatusController {

    @Autowired
    private UsersStatusService usersStatusService;

    @GetMapping(value = {"/"})
    public ArrayList<UsersStatus> getStatuses() {
        try {
            return usersStatusService.getAll(null);
        } catch (TravelBookException e) {
            log.error("error fetching public statuses", e);
            return new ArrayList<>();
        }
    }

    @PostMapping(value = {"/"})
    public TravelBookApiResponse createUpdate(@RequestBody UsersStatus usersStatus) {
        if (usersStatus.getUserStatusId() != null && usersStatus.getUserStatusId() > 0)
            return usersStatusService.update(usersStatus);
        return usersStatusService.create(usersStatus);
    }

//    @PutMapping(value = {"/{userStatusId}"})
//    public TravelBookApiResponse update(@PathVariable(value = "userStatusId") Integer userStatusId,
//                                        @RequestBody UsersStatus usersStatus) {
//        return usersStatusService.update(usersStatus);
//    }

    @GetMapping(value = {"/{userStatusId}"})
    public UsersStatus getStatuses(@PathVariable(value = "userStatusId") Integer userStatusId) {

        try {
            return usersStatusService.getByParam(userStatusId);
        } catch (TravelBookException e) {
            log.error("error fetching status with id " + userStatusId, e);
            return null;
        }
    }

    @PostMapping(value = {"getUserStatuses"})
    public ArrayList<UsersStatus> getUserStatuses(@RequestBody Map<String, Object> param) {
        try {
            return usersStatusService.getAll(param);
        } catch (TravelBookException e) {
            log.error("error fetching statuses for current user", e);
            return new ArrayList<>();
        }
    }

}
