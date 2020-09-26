package org.travelbook.backend.dao.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


public class Base implements Serializable {

    @Getter
    @Setter
    private Date createTime;

    @Getter
    @Setter
    private Date updateTime;


}
