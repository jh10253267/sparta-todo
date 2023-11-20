package com.sparta.spartatodo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    //최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)
    private String mid;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]{8,15}+$")
    //최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)
    private String mpw;
}
