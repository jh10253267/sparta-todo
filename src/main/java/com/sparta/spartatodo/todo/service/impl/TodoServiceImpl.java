package com.sparta.spartatodo.todo.service.impl;

import com.sparta.spartatodo.global.exception.CustomTodoException;
import com.sparta.spartatodo.global.exception.ErrorCode;
import com.sparta.spartatodo.todo.domain.Todo;
import com.sparta.spartatodo.todo.dto.TodoRequestDTO;
import com.sparta.spartatodo.todo.dto.TodoResponseDTO;
import com.sparta.spartatodo.todo.repository.TodoRepository;
import com.sparta.spartatodo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public TodoResponseDTO register(TodoRequestDTO todoRequestDTO, String username) {
        Todo todo = Todo.builder()
                .title(todoRequestDTO.getTitle())
                .content(todoRequestDTO.getContent())
                .writer(username)
                .build();
        Todo resultTodo = todoRepository.save(todo);
        TodoResponseDTO todoResponseDTO = modelMapper.map(resultTodo, TodoResponseDTO.class);

        return todoResponseDTO;
    }

    @Override
    public TodoResponseDTO read(Long tno) {
        Todo todo = todoRepository.findById(tno).orElseThrow(() ->
                new CustomTodoException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", tno))
        );

        return modelMapper.map(todo, TodoResponseDTO.class);

    }

//    @Override
//    public PageResponseDTO<TodoResponseDTO> list(PageRequestDTO pageRequestDTO) {
//        Page<TodoResponseDTO> result = todoRepository.searchWithQuery(pageRequestDTO);
//        return PageResponseDTO.<TodoResponseDTO>withAll()
//                .pageRequestDTO(pageRequestDTO)
//                .total((int)result.getTotalElements())
//                .build();
//    }

    @Override
    public void remove(Long tno) {
        Todo todo = todoRepository.findById(tno).orElseThrow(() ->
                new CustomTodoException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", tno))
        );

        todoRepository.delete(todo);
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

        return modelMapper.map(modTodo, TodoResponseDTO.class);
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

