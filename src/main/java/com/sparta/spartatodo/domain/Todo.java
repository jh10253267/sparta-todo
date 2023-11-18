package com.sparta.spartatodo.domain;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_todo_api")
public class Todo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length=500, nullable = false)
    private String content;
    @Column(length = 50, nullable = false)
    private String writer;
    @ColumnDefault("0")
    @Column(columnDefinition = "TINYINT(1)")
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
