package com.sparta.spartatodo.reply.domain;

import com.sparta.spartatodo.global.domain.BaseEntity;
import com.sparta.spartatodo.todo.domain.Todo;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "todo")
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_todo_tno", columnList = "todo_tno")
})
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne(fetch = FetchType.LAZY)
    private Todo todo;
    private String replyText;
    private String replyWriter;

    public void changeText(String replyText) {
        this.replyText = replyText;
    }

}
