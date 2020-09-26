package org.travelbook.backend.dao.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
public class User extends Base {

    @Getter
    @Setter
    private Integer userId;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String name;

}
