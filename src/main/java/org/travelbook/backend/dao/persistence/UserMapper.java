package org.travelbook.backend.dao.persistence;

import org.travelbook.backend.dao.domain.User;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;
import java.util.Map;

public interface UserMapper {

    int create(User user) throws TravelBookException;

    ArrayList<User> getAll() throws TravelBookException;

    User getByParam(Map<String, Object> param) throws TravelBookException;

    boolean isDuplicateEmail(Map<String, Object> param) throws TravelBookException;
}
