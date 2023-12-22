package com.sparta.spartatodo.apiuser.controller;

import com.sparta.spartatodo.apiuser.dto.MidCheckDTO;
import com.sparta.spartatodo.apiuser.dto.SignUpRequestDTO;
import com.sparta.spartatodo.apiuser.service.UserService;
import com.sparta.spartatodo.global.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public Response<Void> signup(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        userService.signUp(signUpRequestDTO);
        return Response.success();
    }

    // TODO : implement
    @PostMapping("/check")
    public Response<Boolean> validateUsername(@RequestBody MidCheckDTO midCheckDTO) {
        boolean temp =  userService.checkUsernameDuplication(midCheckDTO.getMid());
        return Response.success(temp);
    }

}
