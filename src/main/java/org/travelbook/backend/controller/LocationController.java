package org.travelbook.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.travelbook.backend.dao.domain.Location;
import org.travelbook.backend.service.LocationService;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping(value = "location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping(value = {"/"})
    public ArrayList<Location> getLocations() {
        try {
            return locationService.getAll();
        } catch (TravelBookException e) {
            log.error("error fetching locations", e);
            return new ArrayList<>();
        }
    }
}
