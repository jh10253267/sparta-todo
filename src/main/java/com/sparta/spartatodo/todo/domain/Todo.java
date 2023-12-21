package com.sparta.spartatodo.todo.domain;

import com.sparta.spartatodo.global.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_todo_api")
@SQLDelete(sql = "UPDATE tbl_todo_api SET deleted_at = NOW() where tno=?")
@Where(clause = "deleted_at is NULL")
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

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

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
