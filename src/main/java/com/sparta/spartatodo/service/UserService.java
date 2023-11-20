package com.sparta.spartatodo.service;

import com.sparta.spartatodo.dto.SignUpRequestDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    void signUp(SignUpRequestDTO signUpRequestDTO);

}
