package com.sparta.spartatodo.dto;

import com.sparta.spartatodo.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    @NotBlank
    private String replyText;
    private String replyWriter;
    private LocalDateTime createdAt;
}
