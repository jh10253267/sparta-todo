package com.sparta.spartatodo.service;

import com.sparta.spartatodo.dto.TodoDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TodoService {
    Long register(TodoDTO todoDTO);
    TodoDTO read(Long tno);
    List<TodoDTO> readAll();
    void remove(Long tno);
    void modify(TodoDTO todoDTO);
}
