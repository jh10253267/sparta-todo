package com.sparta.spartatodo.service.impl;

import com.sparta.spartatodo.domain.APIUser;
import com.sparta.spartatodo.dto.SignUpRequestDTO;
import com.sparta.spartatodo.repository.APIUserRepository;
import com.sparta.spartatodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final APIUserRepository apiUserRepository;
    private final ModelMapper modelMapper;

    @Override
    public void signUp(SignUpRequestDTO signUpRequestDTO) {
        Optional<APIUser> result = apiUserRepository.findById(signUpRequestDTO.getMid());
        if (result.isPresent()) {
            return;
        }

        APIUser apiUser = modelMapper.map(signUpRequestDTO, APIUser.class);
        apiUserRepository.save(apiUser);
    }
}
