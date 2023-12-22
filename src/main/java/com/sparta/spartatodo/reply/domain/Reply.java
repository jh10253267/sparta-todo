package com.sparta.spartatodo.reply.domain;

import com.sparta.spartatodo.global.domain.BaseEntity;
import com.sparta.spartatodo.todo.domain.Todo;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "todo")
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_todo_tno", columnList = "todo_tno")
})
@SQLDelete(sql = "UPDATE reply SET deleted_at = NOW() where rno=?")
@Where(clause = "deleted_at is NULL")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne(fetch = FetchType.LAZY)
    private Todo todo;
    private String replyText;
    private String writer;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    public void changeText(String replyText) {
        this.replyText = replyText;
    }

}
