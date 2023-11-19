package com.sparta.spartatodo.domain;

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
public class Reply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne(fetch = FetchType.LAZY)
    private Todo todo;
    private String replyText;
    private String replyWriter;

}
