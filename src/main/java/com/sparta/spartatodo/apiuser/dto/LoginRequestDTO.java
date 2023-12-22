package com.sparta.spartatodo.apiuser.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class LoginRequestDTO extends User {
    private String mid;
    private String mpw;


    public LoginRequestDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.mid = username;
        this.mpw = password;
    }
}
