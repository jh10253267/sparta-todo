package com.sparta.spartatodo.todo.service;

import com.sparta.spartatodo.global.request.PageRequestDTO;
import com.sparta.spartatodo.global.request.PageResponseDTO;
import com.sparta.spartatodo.todo.domain.Todo;
import com.sparta.spartatodo.todo.dto.TodoRequestDTO;
import com.sparta.spartatodo.todo.dto.TodoResponseDTO;
import com.sparta.spartatodo.todo.repository.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Log4j2
class TodoServiceTest {
    @Autowired
    TodoService sut;
    @MockBean
    private TodoRepository todoRepository;
    @MockBean
    private ModelMapper modelMapper;

//    @DisplayName("[Todo] [Service] [ReadOne]")
//    @Test
//    void givenTno_whenDoRead_thenReturnsTodoDTOData() {
//        //Given
//        Long tno = 1L;
//        when(todoRepository.findById(tno)).thenReturn(Optional.of(mock(Todo.class)));
//
//        //When
//        TodoResponseDTO todoResponseDTO = sut.read(tno);
//
//        //Then
//        assertThat(todoResponseDTO).isNotNull();
//    }
//
//    @DisplayName("[Todo] [Service] [Register]")
//    @Test
//    void givenTestDTOData_whenDoRegister_thenReturnsLongValue() {
//        TodoRequestDTO todoRequestDTO = TodoRequestDTO.builder()
//                .title("test title")
//                .content("contest test")
//                .build();
//        String username = "";
//
//        when(todoRepository.save(any())).thenReturn(Optional.of(mock(Todo.class)));
////        when(modelMapper.map()).thenReturn(mock(TodoResponseDTO.class));
//
//        TodoResponseDTO todoResponseDTO = sut.register(todoRequestDTO, username);
//        log.info(todoRequestDTO);
//        assertThat(todoResponseDTO).isNotNull();
//    }
//
//    @DisplayName("[Todo] [Service] [Remove]")
//    @Test
//    void givenTno_whenDoRemove_thenFindThatTnoAndReturnException() {
//        //Given
//        Long tno = 50L;
//        //When
//        sut.remove(tno, "");
//        //Then
//        assertThatCode(() -> sut.read(tno)).
//                isInstanceOf(NoSuchElementException.class);
//    }
//
//    @DisplayName("[Todo] [Service] [Modify]")
//    @Test
//    void givenTodoDTO_whenDoModify_thenCheckIsEntityModified() {
//        //Given
//        Long tno = 1L;
//        String modTitle = "modifyTest...";
//        TodoRequestDTO todoRequestDTO = TodoRequestDTO.builder()
//                .title("test")
//                .content("test")
//                .build();
//
//        //When
//        sut.modify(tno, todoRequestDTO, "string");
//
//        //Then
//        TodoResponseDTO modDto = sut.read(tno);
//        assertThat(modDto.getTitle()).isEqualTo(modTitle);
//    }
//
//
//    @Test
//    void 검색기능_테스트() {
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .type("tcw")
//                .keyword("Urban")
//                .page(1)
//                .size(10)
//                .build();
//
//        PageResponseDTO<TodoResponseDTO> responseDTO = sut.list(pageRequestDTO);
//
//        log.info(responseDTO);
//    }
}