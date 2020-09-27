package org.travelbook.backend.dao.persistence;

import org.travelbook.backend.dao.domain.UsersStatus;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;
import java.util.Map;

public interface UsersStatusMapper {

    int create(UsersStatus usersStatus) throws TravelBookException;

    int update(UsersStatus usersStatus) throws TravelBookException;

    ArrayList<UsersStatus> getAll(Map<String, Object> param) throws TravelBookException;

    UsersStatus getByParam(Map<String, Object> param) throws TravelBookException;

}
