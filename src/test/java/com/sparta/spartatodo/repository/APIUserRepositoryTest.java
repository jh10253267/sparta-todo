package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.domain.APIUser;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class APIUserRepositoryTest {
    @Autowired
    private APIUserRepository apiUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @DisplayName("APIUser 레포지토리 데이터 삽입 테스트")
    @Test
    void givenAPIUserDummyData_whenDoSave_thenInsertAPIUserDataIntoDatabase() {
        IntStream.rangeClosed(1, 50).forEach(i ->{
            APIUser apiUser = APIUser.builder()
                    .mid("apiuser"+i)
                    .mpw(passwordEncoder.encode("1234"))
                    .build();
            apiUserRepository.save(apiUser);
                });
    }

}