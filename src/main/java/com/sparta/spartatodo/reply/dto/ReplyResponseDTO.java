package com.sparta.spartatodo.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponseDTO {
    private String replyText;
    private String replyWriter;
    private LocalDateTime createdAt;
}
