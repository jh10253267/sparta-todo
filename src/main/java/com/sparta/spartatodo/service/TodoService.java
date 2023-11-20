package com.sparta.spartatodo.service;

import com.sparta.spartatodo.dto.PageRequestDTO;
import com.sparta.spartatodo.dto.PageResponseDTO;
import com.sparta.spartatodo.dto.TodoRequestDTO;
import com.sparta.spartatodo.dto.TodoResponseDTO;
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
