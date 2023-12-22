package com.sparta.spartatodo.todo.service;

import com.sparta.spartatodo.global.request.PageRequestDTO;
import com.sparta.spartatodo.global.request.PageResponseDTO;
import com.sparta.spartatodo.todo.dto.TodoRequestDTO;
import com.sparta.spartatodo.todo.dto.TodoResponseDTO;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface TodoService {
    TodoResponseDTO register(TodoRequestDTO todoRequestDTO, String username);
    TodoResponseDTO read(Long tno);
    PageResponseDTO<TodoResponseDTO> list(PageRequestDTO pageRequestDTO);
    void remove(Long tno, String username);
    TodoResponseDTO modify(Long tno, TodoRequestDTO todoRequestDTO, String username);
    TodoResponseDTO updateComplete(Long tno, String username);
}
