package com.sparta.spartatodo.repository.search.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.sparta.spartatodo.domain.QReply;
import com.sparta.spartatodo.domain.QTodo;
import com.sparta.spartatodo.domain.Todo;
import com.sparta.spartatodo.dto.PageRequestDTO;
import com.sparta.spartatodo.dto.TodoListReplyCountDTO;
import com.sparta.spartatodo.dto.ReplyDTO;
import com.sparta.spartatodo.dto.TodoResponseDTO;
import com.sparta.spartatodo.repository.search.TodoSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {
    public TodoSearchImpl() {
        super(Todo.class);
    }
    @Override
    public Page<TodoResponseDTO> searchWithQuery(PageRequestDTO pageRequestDTO) {
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);
        query.groupBy(todo);

        if(pageRequestDTO.getKeyword() != null){
            query.where(todo.title.contains(pageRequestDTO.getKeyword()));
        }

        this.getQuerydsl().applyPagination(pageRequestDTO.getPageable("tno"), query);

        JPQLQuery<TodoResponseDTO> dtojpqlQuery = query.select(Projections.bean(TodoResponseDTO.class,
                todo.title,
                todo.content,
                todo.writer,
                todo.complete,
                todo.createdAt
                ));


        List<TodoResponseDTO> todoResponseDTOList = dtojpqlQuery.fetch();

        long count = dtojpqlQuery.fetchCount();

        return new PageImpl<>(todoResponseDTOList, pageRequestDTO.getPageable("tno"), count);
    }
}
