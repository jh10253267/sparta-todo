package com.sparta.spartatodo.controller;


import com.sparta.spartatodo.domain.Todo;
import com.sparta.spartatodo.dto.PageRequestDTO;
import com.sparta.spartatodo.dto.PageResponseDTO;
import com.sparta.spartatodo.dto.TodoRequestDTO;
import com.sparta.spartatodo.dto.TodoResponseDTO;
import com.sparta.spartatodo.service.TodoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/todos")
public class TodoMainController {
    private final TodoService todoService;

    @ApiOperation(value = "Post new Todo",notes="POST 방식으로 새로운 할일 등록" )
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> register(@Valid @RequestBody TodoRequestDTO todoRequestDTO, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Map<String, Object> map = new HashMap<>();

        TodoResponseDTO todoResponseDTO = todoService.register(todoRequestDTO);
        log.info(todoResponseDTO);

        map.put("Your Todo", todoResponseDTO);
        return map;
    }
    @GetMapping("/{tno}")
    public Map<String, Object> todo(@PathVariable("tno") Long tno) {
        Map<String, Object> map = new HashMap<>();
        TodoResponseDTO todoResponseDTO = todoService.read(tno);

        map.put("todo", todoResponseDTO);
        return map;
    }
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponseDTO<TodoResponseDTO> list(PageRequestDTO pageRequestDTO) {
        return todoService.list(pageRequestDTO);
    }
    @PutMapping(value="/{tno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> modify(@PathVariable("tno") Long tno, @RequestBody TodoRequestDTO todoRequestDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Map<String, Object> map = new HashMap<>();

        TodoResponseDTO todoResponseDTO = todoService.modify(tno,todoRequestDTO);
        map.put("Modified Todo", todoResponseDTO);

        return map;
    }
    @PostMapping("/{tno}")
    public void updateComplete(@PathVariable("tno") Long tno, @AuthenticationPrincipal UserDetails userDetails) {
        todoService.updateComplete(tno, userDetails.getUsername());
    }




}
