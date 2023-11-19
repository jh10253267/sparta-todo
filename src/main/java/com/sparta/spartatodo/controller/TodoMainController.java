package com.sparta.spartatodo.controller;

import com.sparta.spartatodo.dto.TodoRegisterRequestDTO;
import com.sparta.spartatodo.dto.TodoResponseDTO;
import com.sparta.spartatodo.service.TodoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/todos")
public class TodoMainController {
    private final TodoService todoService;

    @ApiOperation(value = "Post new Todo",notes="POST 방식으로 새로운 투두 등록" )
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> register(@Valid @RequestBody TodoRegisterRequestDTO todoRegisterRequestDTO, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Map<String, Object> map = new HashMap<>();

        TodoResponseDTO todoResponseDTO = todoService.register(todoRegisterRequestDTO);
        log.info(todoResponseDTO);

        map.put("Your Todo", todoResponseDTO);
        return map;
    }


}
