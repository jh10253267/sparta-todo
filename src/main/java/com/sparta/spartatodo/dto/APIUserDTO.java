package com.sparta.spartatodo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@ToString
@Setter
public class APIUserDTO extends User {
    private String mid;
    private String mpw;


    public APIUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.mid = username;
        this.mpw = password;
    }
}
