package com.sparta.spartatodo.apiuser.service.impl;

import com.sparta.spartatodo.apiuser.domain.APIUser;
import com.sparta.spartatodo.apiuser.dto.SignUpRequestDTO;
import com.sparta.spartatodo.apiuser.repository.APIUserRepository;
import com.sparta.spartatodo.apiuser.service.UserService;
import com.sparta.spartatodo.global.exception.CustomTodoException;
import com.sparta.spartatodo.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final APIUserRepository apiUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public APIUser signUp(SignUpRequestDTO signUpRequestDTO) {
        apiUserRepository.findByMid(signUpRequestDTO.getMid()).ifPresent(it -> {
            throw new CustomTodoException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", signUpRequestDTO.getMid()));
        });

        APIUser apiUser = APIUser.builder()
                .mid(signUpRequestDTO.getMid())
                .mpw(passwordEncoder.encode(signUpRequestDTO.getMpw()))
                .build();
        APIUser signedUser = apiUserRepository.save(apiUser);
        return signedUser;
    }

    @Override
    public boolean checkUsernameDuplication(String username) {
        return apiUserRepository.existsAPIUserByMid(username);
    }
}
