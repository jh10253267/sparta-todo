package com.sparta.spartatodo.service.impl;

import com.sparta.spartatodo.domain.Todo;
import com.sparta.spartatodo.dto.TodoRequestDTO;
import com.sparta.spartatodo.dto.TodoResponseDTO;
import com.sparta.spartatodo.repository.TodoRepository;
import com.sparta.spartatodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public TodoResponseDTO register(TodoRequestDTO todoRequestDTO) {
        Todo todo = modelMapper.map(todoRequestDTO, Todo.class);
        Todo resultTodo = todoRepository.save(todo);
        TodoResponseDTO todoResponseDTO = modelMapper.map(resultTodo, TodoResponseDTO.class);

        return todoResponseDTO;
    }

    @Override
    public TodoResponseDTO read(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        return modelMapper.map(todo, TodoResponseDTO.class);

    }

    @Override
    public List<TodoResponseDTO> readAll() {
        List<Todo> todos = todoRepository.findAllByOrderByCreatedAtDesc();

        return todos.stream()
                .map(todo -> modelMapper.map(todo, TodoResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    @Override
    public TodoResponseDTO modify(Long tno, TodoRequestDTO todoRequestDTO) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        todo.changeTitle(todoRequestDTO.getTitle());
        todo.changeContent(todoRequestDTO.getContent());

        Todo modTodo = todoRepository.save(todo);
        TodoResponseDTO todoResponseDTO = modelMapper.map(modTodo, TodoResponseDTO.class);
        return todoResponseDTO;
    }

    @Override
    public void updateComplete(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();

        todo.changeComplete(true);
        todoRepository.save(todo);
    }


}

