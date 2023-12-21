package com.sparta.spartatodo.apiuser.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sparta.spartatodo.apiuser.domain.APIUser;
import com.sparta.spartatodo.apiuser.dto.SignUpRequestDTO;
import com.sparta.spartatodo.apiuser.service.UserService;
import com.sparta.spartatodo.global.exception.CustomTodoException;
import com.sparta.spartatodo.global.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    // TODO : 통합테스트에선 잘 되는데 이상하게 테스트환경에선 필터에 걸리는 것 같습니다. 그래서 일단 인증정보를 설정해놓았습니다.

    @Test
    @WithMockUser
    void 회원가입() throws Exception {
        String username = "testuser";
        String password = "123124234";

        SignUpRequestDTO dto = new SignUpRequestDTO(username, password);

        when(userService.signUp(dto)).thenReturn(mock(APIUser.class));

        mvc.perform(post("/api/v1/users/signup")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(new SignUpRequestDTO(username, password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void 회원가입_요청_유효성검사_실패() throws Exception {
        String username = "user1";
        String password = "123124234";

        SignUpRequestDTO dto = new SignUpRequestDTO(username, password);

        when(userService.signUp(dto)).thenThrow(new CustomTodoException(ErrorCode.DUPLICATED_USER_NAME));

        mvc.perform(post("/api/v1/users/signup")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(new SignUpRequestDTO(username, password)))
                ).andDo(print())
                .andExpect(status().isConflict());
    }

    // TODO : 이부분을 고민해봤는데 유효성 검사 결과를 프론트에서 어떻게 받아서 사용하는지 헷갈립니다.
    //  그저 프론트단에서 빠르게 유효성 검증 결과를 받기 위한 체크 api라서 예외처리는 프론트에서 하고 따로 성공 케이스와 실패케이스를 나누지 않았습니다.
    //  만약 유저가 프론트 유효성검증을 우회했을 경우를 대비해서 회원가입 서비스로직 내부에서 한 번 더 체크하도록 했습니다.

    @Test
    @WithMockUser
    void 아이디_유효성검사_테스트() throws Exception {
        String username = "user1";
        String password = "123124234";

        when(userService.checkUsernameDuplication(username)).thenReturn(false);

        mvc.perform(post("/api/v1/users/check")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(new SignUpRequestDTO(username, password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

}