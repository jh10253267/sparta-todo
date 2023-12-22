package com.sparta.spartatodo.todo.controller;

import com.sparta.spartatodo.global.response.Response;
import com.sparta.spartatodo.global.request.PageRequestDTO;
import com.sparta.spartatodo.global.request.PageResponseDTO;
import com.sparta.spartatodo.todo.dto.TodoRequestDTO;
import com.sparta.spartatodo.todo.dto.TodoResponseDTO;
import com.sparta.spartatodo.todo.service.TodoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private final TodoService todoService;

    @ApiOperation(value = "Post new Todo",notes="POST 방식으로 새로운 할일 등록" )
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<TodoResponseDTO> register(@Valid @RequestBody TodoRequestDTO todoRequestDTO, BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails) throws BindException {
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        TodoResponseDTO todoResponseDTO = todoService.register(todoRequestDTO, userDetails.getUsername());

        return Response.success(todoResponseDTO);
    }

    @ApiOperation(value = "Get Todo",notes="투두 단 건 조회" )
    @GetMapping("/{tno}")
    public Response<TodoResponseDTO> read(@PathVariable("tno") Long tno) {
        TodoResponseDTO todoResponseDTO = todoService.read(tno);

        return Response.success(todoResponseDTO);
    }

    @ApiOperation(value = "Get Todos",notes="투두 리스트 조회")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<PageResponseDTO> list(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<TodoResponseDTO> responseDTO = todoService.list(pageRequestDTO);

        log.info(responseDTO);

        return Response.success(responseDTO);
    }

    @ApiOperation(value = "Put Todo",notes="투두 단 건 수정" )
    @PutMapping(value="/{tno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<TodoResponseDTO> modify(@PathVariable("tno") Long tno, @Valid @RequestBody TodoRequestDTO todoRequestDTO, @AuthenticationPrincipal UserDetails userDetails) {
        TodoResponseDTO todoResponseDTO = todoService.modify(tno,todoRequestDTO, userDetails.getUsername());

        return Response.success(todoResponseDTO);
    }
    @ApiOperation(value = "Update Todo complete statement",notes="투두 완료상태로 변경")
    @PostMapping("/{tno}")
    public Response<Void> updateComplete(@PathVariable("tno") Long tno, @AuthenticationPrincipal UserDetails userDetails) {
        todoService.updateComplete(tno, userDetails.getUsername());

        return Response.success();
    }

    @DeleteMapping("/{tno}")
    public Response<Void> delete(@PathVariable("tno") Long tno, @AuthenticationPrincipal UserDetails userDetails) {
        todoService.remove(tno, userDetails.getUsername());

        return Response.success();

    }




}
