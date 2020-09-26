package org.travelbook.backend.dao.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Location extends Base {

    @Getter
    @Setter
    private Integer locationId;

    @Getter
    @Setter
    private String location;

}
