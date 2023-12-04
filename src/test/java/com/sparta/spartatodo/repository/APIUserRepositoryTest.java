package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.apiuser.domain.APIUser;
import com.sparta.spartatodo.apiuser.repository.APIUserRepository;
import com.sparta.spartatodo.config.JpaConfig;
import com.sparta.spartatodo.config.TestConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@Log4j2
@DisplayName("API User Repository테스트")
@DataJpaTest
@ExtendWith(MockitoExtension.class)
@Import(TestConfig.class)
class APIUserRepositoryTest {
    @Autowired
    private APIUserRepository apiUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("APIUser 레포지토리 데이터 삽입 테스트")
    @Test
    void givenAPIUserDummyData_whenDoSave_thenInsertAPIUserDataIntoDatabase() {
        // Given
        int dataCount = 50;
        IntStream.rangeClosed(1, dataCount).forEach(i ->{
            APIUser apiUser = APIUser.builder()
                    .mid("apiuser"+i)
                    .mpw(passwordEncoder.encode("1234"))
                    .build();
        // When
            apiUserRepository.save(apiUser);
                });
        // Then
        assertThat(apiUserRepository.count()).isEqualTo(dataCount);
    }

}