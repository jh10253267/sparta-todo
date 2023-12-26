package com.sparta.spartatodo.apiuser.service;

import com.sparta.spartatodo.apiuser.domain.APIUser;
import com.sparta.spartatodo.apiuser.dto.SignUpRequestDTO;
import com.sparta.spartatodo.apiuser.repository.APIUserRepository;
import com.sparta.spartatodo.global.exception.CustomTodoException;
import com.sparta.spartatodo.global.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService sut;
    @MockBean
    private APIUserRepository apiUserRepository;
    @MockBean
    private BCryptPasswordEncoder encoder;

//    @Test
//    void 회원가입이_정상적으로_동작하는경우() {
//        String username = "username";
//        String password = "password";
//
//        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO(username, password);
//
//        when(apiUserRepository.findById(username)).thenReturn(Optional.empty());
//        when(encoder.encode(password)).thenReturn("encrypt_password");
//        when(apiUserRepository.save(any())).thenReturn(mock(APIUser.class));
//
//        assertThatCode(() -> sut.signUp(signUpRequestDTO)).doesNotThrowAnyException();
//
//    }
//
//    @Test
//    void 회원가입시_이미존재하는_아이디일경우() {
//        String username = "username";
//        String password = "password";
//
//        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO(username, password);
//
//        when(apiUserRepository.findById(username)).thenThrow(new CustomTodoException(ErrorCode.DUPLICATED_USER_NAME));
//        when(encoder.encode(password)).thenReturn("encrypt_password");
//        when(apiUserRepository.save(any())).thenReturn(mock(APIUser.class));
//
//        assertThatCode(() -> sut.signUp(signUpRequestDTO)).isInstanceOf(CustomTodoException.class);
//    }
//
//    @Test
//    void 아이디_중복체크() {
//        String username = "username";
//        when(apiUserRepository.existsAPIUserByMid(username)).thenReturn(false);
//        boolean isDuplicated = sut.checkUsernameDuplication(username);
//
//        assertThat(isDuplicated).isFalse();
//    }
}