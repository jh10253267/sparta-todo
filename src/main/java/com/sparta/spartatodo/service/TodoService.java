package com.sparta.spartatodo.service;

import com.sparta.spartatodo.dto.TodoRegisterRequestDTO;
import com.sparta.spartatodo.dto.TodoResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TodoService {
    TodoResponseDTO register(TodoRegisterRequestDTO todoRequestDTO);
    TodoResponseDTO read(Long tno);
    List<TodoResponseDTO> readAll();
    void remove(Long tno);
    void modify(TodoRegisterRequestDTO todoRequestDTO);
}
