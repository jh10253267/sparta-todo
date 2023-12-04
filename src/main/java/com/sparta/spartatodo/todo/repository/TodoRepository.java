package com.sparta.spartatodo.todo.repository;

import com.sparta.spartatodo.todo.domain.Todo;
import com.sparta.spartatodo.todo.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

}
