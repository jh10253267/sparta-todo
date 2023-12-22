package com.sparta.spartatodo.todo.service.impl;

import com.sparta.spartatodo.global.exception.CustomTodoException;
import com.sparta.spartatodo.global.exception.ErrorCode;
import com.sparta.spartatodo.todo.domain.Todo;
import com.sparta.spartatodo.global.request.PageRequestDTO;
import com.sparta.spartatodo.global.request.PageResponseDTO;
import com.sparta.spartatodo.todo.dto.TodoRequestDTO;
import com.sparta.spartatodo.todo.dto.TodoResponseDTO;
import com.sparta.spartatodo.todo.repository.TodoRepository;
import com.sparta.spartatodo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public PageResponseDTO<TodoResponseDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("tno");
        Page<Todo> result = todoRepository.searchAll(types, keyword, pageable);

        List<TodoResponseDTO> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoResponseDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<TodoResponseDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public void remove(Long tno, String username) {
        Todo todo = todoRepository.findById(tno).orElseThrow(() ->
                new CustomTodoException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", tno))
        );
        if (!todo.getWriter().equals(username)) {
            throw new CustomTodoException(ErrorCode.INVALID_ID_PERMISSION, null);
        }

        todoRepository.delete(todo);
    }

    @Override
    public TodoResponseDTO modify(Long tno, TodoRequestDTO todoRequestDTO, String username) {
        Todo todo = todoRepository.findById(tno).orElseThrow(() ->
                new CustomTodoException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", tno))
        );

        if (!todo.getWriter().equals(username)) {
            throw new CustomTodoException(ErrorCode.INVALID_ID_PERMISSION, null);
        }

        todo.changeTitle(todoRequestDTO.getTitle());
        todo.changeContent(todoRequestDTO.getContent());

        Todo modTodo = todoRepository.save(todo);

        return modelMapper.map(modTodo, TodoResponseDTO.class);
    }

    @Override
    public TodoResponseDTO updateComplete(Long tno, String username) {
        Todo todo = todoRepository.findById(tno).orElseThrow(() ->
                new CustomTodoException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", tno))
        );

        if(!todo.getWriter().equals(username)) {
            throw new CustomTodoException(ErrorCode.INVALID_ID_PERMISSION, null);
        }

        todo.changeComplete(true);
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoResponseDTO.class);
    }
}

