package com.sparta.spartatodo.service;

import com.sparta.spartatodo.todo.dto.TodoRequestDTO;
import com.sparta.spartatodo.todo.dto.TodoResponseDTO;
import com.sparta.spartatodo.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@Log4j2
class TodoServiceTest {
    @Autowired
    TodoService sut;

    @DisplayName("[Todo] [Service] [ReadOne]")
    @Test
    void givenTno_whenDoRead_thenReturnsTodoDTOData() {
        //Given
        Long tno = 1L;
        //When
        TodoResponseDTO todoResponseDTO = sut.read(tno);
        //Then
        log.info(todoResponseDTO);
        assertThat(todoResponseDTO).isNotNull();
    }

    @DisplayName("[Todo] [Service] [Register]")
    @Test
    void givenTestDTOData_whenDoRegister_thenReturnsLongValue() {
        TodoRequestDTO todoRequestDTO = TodoRequestDTO.builder()
                .title("test title")
                .content("contest test")
                .build();

        TodoResponseDTO todoResponseDTO = sut.register(todoRequestDTO);
        log.info(todoRequestDTO);
        assertThat(todoResponseDTO).isNotNull();
    }

//    @DisplayName("[Todo] [Service] [ReadAll]")
//    @Test
//    void givenNothing_whenDoReadAll_thenReturnsAllTodoDTOList() {
//        //Given
//        //When
//        List<TodoResponseDTO> todos = sut.readAll();
//        //Then
//        log.info("todos length: " + todos.size());
//    }

    @DisplayName("[Todo] [Service] [Remove]")
    @Test
    void givenTno_whenDoRemove_thenFindThatTnoAndReturnException() {
        //Given
        Long tno = 50L;
        //When
        sut.remove(tno);
        //Then
        assertThatCode(() -> sut.read(tno)).
                isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("[Todo] [Service] [Modify]")
    @Test
    void givenTodoDTO_whenDoModify_thenCheckIsEntityModified() {
        //Given
        Long tno = 1L;
        String modTitle = "modifyTest...";
        TodoRequestDTO todoRequestDTO = TodoRequestDTO.builder()
                .title("test")
                .content("test")
                .build();

        //When
        sut.modify(tno, todoRequestDTO, "string");

        //Then
        TodoResponseDTO modDto = sut.read(tno);
        assertThat(modDto.getTitle()).isEqualTo(modTitle);
    }

}