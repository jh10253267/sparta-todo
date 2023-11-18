package com.sparta.spartatodo.service.impl;

import com.sparta.spartatodo.domain.Todo;
import com.sparta.spartatodo.dto.TodoDTO;
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
    public Long register(TodoDTO todoDTO) {
        Todo todo = modelMapper.map(todoDTO, Todo.class);

        Long tno = todoRepository.save(todo).getTno();

        return tno;
    }

    @Override
    public TodoDTO read(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        return modelMapper.map(todo, TodoDTO.class);

    }

    @Override
    public List<TodoDTO> readAll() {
        List<Todo> todos = todoRepository.findAll();

        return todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());
        Todo todo = result.orElseThrow();

        String title = todoDTO.getTitle();
        String content = todoDTO.getContent();
        String writer = todoDTO.getWriter();
        boolean complete = todoDTO.isComplete();
        log.info("input DTO value: " + complete);

        todo.changeTitle(title);
        todo.changeContent(content);
        todo.changeWriter(writer);
        todo.changeComplete(complete);

        //더티체킹에 의해 자동으로 수정된다는 걸 알지만 명시적으로 선언해줬습니다. update를 제외한 crud 기능들은 레포지토리를 통해서
        //DB에 반영이 되는데 업데이트 또한 명시적으로 실행해주는 게 어떨까 생각이 들었습니다.
        //또 컨트롤러단 -> 서비스계층 -> 영속계층으로 요청의 흐름이 진행되기에 처리의 적법성?에 대한 생각이 들어서 이렇게 짜보았습니다.
        todoRepository.save(todo);
    }
}
