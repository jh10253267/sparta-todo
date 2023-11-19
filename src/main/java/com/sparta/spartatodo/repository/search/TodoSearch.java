package com.sparta.spartatodo.repository.search;

import com.sparta.spartatodo.domain.Todo;
import com.sparta.spartatodo.dto.PageRequestDTO;
import com.sparta.spartatodo.dto.ReplyDTO;
import com.sparta.spartatodo.dto.TodoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearch {

    Page<TodoResponseDTO> searchWithQuery(PageRequestDTO pageRequestDTO);



}
