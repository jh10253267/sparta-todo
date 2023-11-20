package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.domain.Reply;
import com.sparta.spartatodo.domain.Todo;
import com.sparta.spartatodo.dto.PageRequestDTO;
import com.sparta.spartatodo.dto.PageResponseDTO;
import com.sparta.spartatodo.dto.TodoResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Log4j2
class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;

    @DisplayName("Todo Repository의 데이터 삽입 기능 테스트")
    @Test
    void givenDummyTodos_whenDoInsertAction_thenNotion() {
        //Given
        //When

        IntStream.rangeClosed(1, 50).forEach(i -> {
            Todo todo = Todo.builder()
                    .title("Todo..." + i)
                    .writer("apiuser"+i)
                    .content("content...")
                    .build();
            todoRepository.save(todo);
        });
        //Then
    }

    @Test
    void nam1e() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .keyword("mod")
                .completed(true)
                .build();

        Page<TodoResponseDTO> result = todoRepository.searchWithQuery(pageRequestDTO);
        result.forEach(todoResponseDTO -> log.info(todoResponseDTO));

    }
}