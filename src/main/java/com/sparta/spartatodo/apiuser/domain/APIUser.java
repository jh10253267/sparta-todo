package com.sparta.spartatodo.apiuser.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "api_user")
public class APIUser {
    @Id
    private String mid;
    private String mpw;

    public void changePw(String mpw) {
        this.mpw = mpw;
    }
}
