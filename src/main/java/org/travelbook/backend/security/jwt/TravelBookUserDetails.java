package org.travelbook.backend.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class TravelBookUserDetails extends User {

    private static final long serialVersionUID = 2024300344862877908L;

    @Getter
    @Setter
    private org.travelbook.backend.dao.domain.User user;

    public TravelBookUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
