package com.sparta.spartatodo.todo.domain;

import com.sparta.spartatodo.global.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_todo_api")
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;
    @Column(length = 100)
    private String title;
    @Column(length = 1000)
    private String content;
    private String writer;
    private boolean complete;

    public void changeTitle(String title) {
        this.title = title;
    }
    public void changeContent(String content) {
        this.content = content;
    }
    public void changeComplete(boolean complete) {
        this.complete = complete;
    }


}
