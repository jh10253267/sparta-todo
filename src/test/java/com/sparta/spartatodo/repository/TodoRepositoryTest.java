package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.todo.domain.Todo;
import com.sparta.spartatodo.todo.dto.PageRequestDTO;
import com.sparta.spartatodo.todo.dto.TodoResponseDTO;
import com.sparta.spartatodo.todo.repository.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@Transactional
@DisplayName("Todo Repository 테스트")
class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;

    @DisplayName("Todo Repository의 데이터 삽입 기능 테스트")
    @Test
    void givenDummyTodos_whenDoInsertAction_thenNotion() {
        //Given
        int inputDataSize = 50;
        //When
        IntStream.rangeClosed(1, inputDataSize).forEach(i -> {
            Todo todo = Todo.builder()
                    .title("Todo..." + i)
                    .writer("apiuser"+i)
                    .content("content...")
                    .build();
            todoRepository.save(todo);
        });
        //Then
        assertThat(todoRepository.count()).isEqualTo(inputDataSize);
    }

//    @Test
//    void nam1e() {
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .keyword("mod")
//                .completed(true)
//                .build();
//
//        Page<TodoResponseDTO> result = todoRepository.searchWithQuery(pageRequestDTO);
//        result.forEach(todoResponseDTO -> log.info(todoResponseDTO));
//
//    }
}