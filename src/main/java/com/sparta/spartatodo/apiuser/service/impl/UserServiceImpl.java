package com.sparta.spartatodo.apiuser.service.impl;

import com.sparta.spartatodo.apiuser.domain.APIUser;
import com.sparta.spartatodo.apiuser.dto.SignUpRequestDTO;
import com.sparta.spartatodo.apiuser.repository.APIUserRepository;
import com.sparta.spartatodo.apiuser.service.UserService;
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
    public void signUp(SignUpRequestDTO signUpRequestDTO) {
        Optional<APIUser> result = apiUserRepository.findById(signUpRequestDTO.getMid());

        log.info(signUpRequestDTO.getMid());
        if (result.isPresent()) {
            throw new RuntimeException("이미 존재하는 아이디입니다");
        }
        APIUser apiUser = APIUser.builder()
                .mid(signUpRequestDTO.getMid())
                .mpw(passwordEncoder.encode(signUpRequestDTO.getMpw()))
                .build();
        apiUserRepository.save(apiUser);
    }
}
