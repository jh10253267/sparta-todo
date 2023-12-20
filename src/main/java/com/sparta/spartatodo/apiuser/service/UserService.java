package com.sparta.spartatodo.apiuser.service;

import com.sparta.spartatodo.apiuser.dto.SignUpRequestDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    void signUp(SignUpRequestDTO signUpRequestDTO);
    boolean checkUsernameDuplication(String username);

}
