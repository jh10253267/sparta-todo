package com.sparta.spartatodo.service;

import com.sparta.spartatodo.dto.TodoRequestDTO;
import com.sparta.spartatodo.dto.TodoResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TodoService {
    TodoResponseDTO register(TodoRequestDTO todoRequestDTO);
    TodoResponseDTO read(Long tno);
    List<TodoResponseDTO> readAll();
    void remove(Long tno);
    TodoResponseDTO modify(Long tno, TodoRequestDTO todoRequestDTO);
    void updateComplete(Long tno);
}
