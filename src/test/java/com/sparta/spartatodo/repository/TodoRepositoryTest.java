package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.domain.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}