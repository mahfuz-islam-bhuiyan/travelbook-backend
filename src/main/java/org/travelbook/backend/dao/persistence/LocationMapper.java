package org.travelbook.backend.dao.persistence;

import org.travelbook.backend.dao.domain.Location;
import org.travelbook.backend.dao.domain.UsersStatus;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;

public interface LocationMapper {

    ArrayList<Location> getAll() throws TravelBookException;

}
