package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
