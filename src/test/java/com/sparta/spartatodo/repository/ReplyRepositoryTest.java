package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.reply.domain.Reply;
import com.sparta.spartatodo.reply.repository.ReplyRepository;
import com.sparta.spartatodo.todo.domain.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;

    @DisplayName("[Reply] [Repository] [Insert]기본 데이터 삽입 테스트")
    @Test
    void givenRealTodoTno_whenDoInsert_thenDoesNotThrowAnyException() {
        //Given
        Long tno = 1L;

        Todo todo = Todo.builder()
                .tno(tno)
                .build();

        Reply reply = Reply.builder()
                .todo(todo)
                .replyText("reply test...")
                .replyWriter("reply writer")
                .build();

        // When & Then
        assertThatCode(() -> replyRepository.save(reply)).doesNotThrowAnyException();
    }

    @DisplayName("[Reply] [Repository] [FindById]")
    @Test
    void givenRno_whenDoFindById_thenReturnsReply() {
        // Given
        Long rno = 1L;

        // When & Then
        Optional<Reply> result = replyRepository.findById(rno);
        assertThatCode(() -> result.orElseThrow()).doesNotThrowAnyException();
    }


}