package com.sparta.spartatodo.todo.repository;

import com.sparta.spartatodo.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
