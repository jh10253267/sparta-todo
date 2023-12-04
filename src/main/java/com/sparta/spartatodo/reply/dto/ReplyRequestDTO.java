package com.sparta.spartatodo.reply.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyRequestDTO {
    private String replyText;
    private String replyWriter;
}
