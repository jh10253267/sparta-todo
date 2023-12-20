package com.sparta.spartatodo.apiuser.controller;

import com.sparta.spartatodo.apiuser.dto.UsernameCheckDTO;
import com.sparta.spartatodo.apiuser.dto.SignUpRequestDTO;
import com.sparta.spartatodo.apiuser.service.UserService;
import com.sparta.spartatodo.global.Response;
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
    public Response<Void> validateUsername(@RequestBody UsernameCheckDTO usernameCheckDTO) {
        boolean temp =  userService.checkUsernameDuplication(usernameCheckDTO.getUserName());
        return Response.success();
    }

}
