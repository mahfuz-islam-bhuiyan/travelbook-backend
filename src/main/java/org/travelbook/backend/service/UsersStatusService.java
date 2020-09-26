package org.travelbook.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.travelbook.backend.utils.Messages;
import org.travelbook.backend.dao.domain.TravelBookApiResponse;
import org.travelbook.backend.dao.domain.UsersStatus;
import org.travelbook.backend.dao.persistence.UsersStatusMapper;
import org.travelbook.backend.utils.TravelBookException;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
@Service
public class UsersStatusService {

    @Autowired
    private UsersStatusMapper usersStatusMapper;

    public TravelBookApiResponse create(UsersStatus usersStatus) {
        TravelBookApiResponse apiResponse = new TravelBookApiResponse(TravelBookApiResponse.SUCCESS_STATUS);

        try {
            usersStatusMapper.create(usersStatus);
        } catch (TravelBookException e) {
            return apiResponse.getErrorApiResponse(log, Messages.Error.INVALID_INPUT, e);
        }

        if (usersStatus.getUserStatusId() != null && usersStatus.getUserStatusId() > 0)
            apiResponse.msg = Messages.Success.USER_STATUS_CREATE_SUCCESSFUL;

        return apiResponse;
    }

    public TravelBookApiResponse update(UsersStatus usersStatus) {
        TravelBookApiResponse apiResponse = new TravelBookApiResponse(TravelBookApiResponse.SUCCESS_STATUS);

        try {
            usersStatusMapper.update(usersStatus);
        } catch (TravelBookException e) {
            return apiResponse.getErrorApiResponse(log, Messages.Error.INVALID_INPUT, e);
        }

        return apiResponse;
    }

    public ArrayList<UsersStatus> getAll() throws TravelBookException {
        return usersStatusMapper.getAll();
    }


    public UsersStatus getByParam(Map<String, Object> param) throws TravelBookException {
        if (param != null)
            return usersStatusMapper.getByParam(param);
        return null;
    }
}
