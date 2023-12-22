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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uno;
    private String mid;
    private String mpw;

    public void changePw(String mpw) {
        this.mpw = mpw;
    }
}
