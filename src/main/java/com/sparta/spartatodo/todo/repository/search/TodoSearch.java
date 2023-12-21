package com.sparta.spartatodo.todo.repository.search;

import com.sparta.spartatodo.todo.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearch {
    Page<Todo> searchAll(String[] types, String keyword, Pageable pageable);
}
