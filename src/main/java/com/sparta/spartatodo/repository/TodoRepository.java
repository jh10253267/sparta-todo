package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.domain.Todo;
import com.sparta.spartatodo.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

    List<Todo> findAllByOrderByCreatedAtDesc();
}
