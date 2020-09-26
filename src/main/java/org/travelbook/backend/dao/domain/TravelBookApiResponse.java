package org.travelbook.backend.dao.domain;

import lombok.ToString;
import org.slf4j.Logger;

@ToString
public class TravelBookApiResponse {

    public static String SUCCESS_STATUS = "success";
    public static String ERROR_STATUS = "error";


    public Integer statusCode;
    public String status;
    public String msg;
    public Object result;

    public TravelBookApiResponse(String status) {
        this.status = status;
    }

    public TravelBookApiResponse getErrorApiResponse(Logger LOGGER, String msg, Exception e) {
        if (LOGGER != null) LOGGER.error(msg, e);

        this.status = ERROR_STATUS;
        this.msg = msg;
        return this;
    }
}
