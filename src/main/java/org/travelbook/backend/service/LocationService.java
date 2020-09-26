package org.travelbook.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.travelbook.backend.dao.domain.Location;
import org.travelbook.backend.dao.persistence.LocationMapper;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;

@Slf4j
@Service
public class LocationService {

    @Autowired
    private LocationMapper locationMapper;


    public ArrayList<Location> getAll() throws TravelBookException {
        return locationMapper.getAll();
    }

}
