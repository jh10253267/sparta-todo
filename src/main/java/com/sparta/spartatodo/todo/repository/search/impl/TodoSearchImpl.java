package com.sparta.spartatodo.todo.repository.search.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.sparta.spartatodo.todo.domain.QTodo;
import com.sparta.spartatodo.todo.domain.Todo;
import com.sparta.spartatodo.todo.repository.search.TodoSearch;
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
    public Page<Todo> searchAll(String[] types, String keyword, Pageable pageable) {
        QTodo todo = QTodo.todo;
        JPQLQuery<Todo> query = from(todo);

        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {

                switch (type) {
                    case "t":
                        booleanBuilder.or(todo.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(todo.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(todo.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(todo.tno.gt(0L));
        query.where(todo.deletedAt.isNull());

        this.getQuerydsl().applyPagination(pageable, query);

        List<Todo> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
