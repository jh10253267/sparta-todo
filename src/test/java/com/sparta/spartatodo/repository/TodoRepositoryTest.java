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
    @Autowired
    private ReplyRepository replyRepository;

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
    void name() {
        Pageable pageable = PageRequest.of(1, 10, Sort.by("tno").descending());

        todoRepository.search1(pageable);
    }

    @Test
    void contains() {
        String keyword = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.searchAll(keyword, pageable);
        log.info(result.getTotalPages());
        log.info(result.getNumber());
        result.getContent().forEach(todo -> log.info(todo));
    }

    @Test
    void sss() {
        Long tno = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.listOfTodo(tno, pageable);
        result.getContent().forEach(reply -> {
            log.info(reply);
        });
    }

//    @Test
//    void nam1e() {
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .keyword("mod")
//                .build();
//
//        Page<TodoResponseDTO> result = todoRepository.searchWithReplies(pageRequestDTO);
//        result.forEach(todoResponseDTO -> log.info(todoResponseDTO));
//
//    }
}