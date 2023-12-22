package com.sparta.spartatodo.todo.repository;

import com.sparta.spartatodo.todo.domain.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Log4j2
class TodoRepositoryTest {
    @Autowired
    private TodoRepository sut;
    @Test
    void searchAllTest() {
        String[] types = {"t", "c", "w"};
        String keyword = "a";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = sut.searchAll(types, keyword, pageable);

        log.info(result.getTotalPages());

        log.info(result.getSize());

        log.info(result.getNumber());

        log.info(result.hasPrevious() + ": " + result.hasNext());

        result.getContent().forEach(todo -> log.info(todo));
    }
}