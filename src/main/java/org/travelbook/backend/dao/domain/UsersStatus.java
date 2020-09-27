package org.travelbook.backend.dao.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.travelbook.backend.dao.enums.PrivacyStatusType;

@ToString
public class UsersStatus extends Base {

    @Getter
    @Setter
    private Integer userStatusId;

    @Getter
    @Setter
    private String statusMessage;

    @Getter
    @Setter
    private PrivacyStatusType privacyStatusType;

//    @Getter
//    @Setter
    private boolean isPinned;

    public boolean getIsPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private Location location;
}
