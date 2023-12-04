package com.sparta.spartatodo.apiuser.controller;

import com.sparta.spartatodo.apiuser.dto.SignUpRequestDTO;
import com.sparta.spartatodo.apiuser.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        userService.signUp(signUpRequestDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return ResponseEntity.ok(map);
    }

}
