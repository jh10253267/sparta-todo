package com.sparta.spartatodo.reply.repository;

import com.sparta.spartatodo.reply.domain.Reply;
import com.sparta.spartatodo.todo.domain.Todo;
import com.sparta.spartatodo.todo.repository.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;
    @Test
    void name() {
        Long tno = 100L;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
        Page<Reply> result = replyRepository.listOfTodo(tno, pageable);

        result.getContent().forEach(reply -> {
            log.info(reply);
        });

    }


}