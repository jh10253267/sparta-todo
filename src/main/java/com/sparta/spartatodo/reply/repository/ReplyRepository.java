package com.sparta.spartatodo.reply.repository;

import com.sparta.spartatodo.reply.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where r.todo.tno = :tno")
    Page<Reply> listOfTodo(Long tno, Pageable pageable);
}
