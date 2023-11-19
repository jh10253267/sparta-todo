package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where r.todo.tno = :tno")
    Page<Reply> listOfTodo(Long tno, Pageable pageable);
}
