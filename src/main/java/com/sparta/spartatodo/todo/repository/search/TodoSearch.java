package com.sparta.spartatodo.todo.repository.search;

import com.sparta.spartatodo.todo.dto.PageRequestDTO;
import com.sparta.spartatodo.todo.dto.TodoResponseDTO;
import org.springframework.data.domain.Page;

public interface TodoSearch {

    Page<TodoResponseDTO> searchWithQuery(PageRequestDTO pageRequestDTO);



}
