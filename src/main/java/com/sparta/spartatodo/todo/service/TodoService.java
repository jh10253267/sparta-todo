package com.sparta.spartatodo.todo.service;

import com.sparta.spartatodo.todo.dto.PageRequestDTO;
import com.sparta.spartatodo.todo.dto.PageResponseDTO;
import com.sparta.spartatodo.todo.dto.TodoRequestDTO;
import com.sparta.spartatodo.todo.dto.TodoResponseDTO;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface TodoService {
    TodoResponseDTO register(TodoRequestDTO todoRequestDTO);
    TodoResponseDTO read(Long tno);
    PageResponseDTO<TodoResponseDTO> list(PageRequestDTO pageRequestDTO);
    void remove(Long tno);
    TodoResponseDTO modify(Long tno, TodoRequestDTO todoRequestDTO, String username);
    void updateComplete(Long tno, String username);
}
