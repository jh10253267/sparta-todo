package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
