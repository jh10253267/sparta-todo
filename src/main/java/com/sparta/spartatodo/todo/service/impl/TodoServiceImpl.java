package com.sparta.spartatodo.todo.service.impl;

import com.sparta.spartatodo.todo.domain.Todo;
import com.sparta.spartatodo.todo.dto.PageRequestDTO;
import com.sparta.spartatodo.todo.dto.PageResponseDTO;
import com.sparta.spartatodo.todo.dto.TodoRequestDTO;
import com.sparta.spartatodo.todo.dto.TodoResponseDTO;
import com.sparta.spartatodo.todo.repository.TodoRepository;
import com.sparta.spartatodo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public PageResponseDTO<TodoResponseDTO> list(PageRequestDTO pageRequestDTO) {
        Page<TodoResponseDTO> result = todoRepository.searchWithQuery(pageRequestDTO);
        return PageResponseDTO.<TodoResponseDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public void remove(Long tno) {

        todoRepository.deleteById(tno);
    }

    @Override
    public TodoResponseDTO modify(Long tno, TodoRequestDTO todoRequestDTO, String username) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        if(!todo.getWriter().equals(username)) {
            throw new RuntimeException();
        }
        todo.changeTitle(todoRequestDTO.getTitle());
        todo.changeContent(todoRequestDTO.getContent());

        Todo modTodo = todoRepository.save(todo);
        TodoResponseDTO todoResponseDTO = modelMapper.map(modTodo, TodoResponseDTO.class);
        return todoResponseDTO;
    }

    @Override
    public void updateComplete(Long tno, String username) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        if(!todo.getWriter().equals(username)) {
            throw new RuntimeException();
        }
        todo.changeComplete(true);
        todoRepository.save(todo);
    }


}

