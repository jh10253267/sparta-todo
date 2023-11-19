package com.sparta.spartatodo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDTO {
    private Long tno;
    private String title;
    private String content;
    private String writer;
    private boolean complete;
    private LocalDateTime createdAt;
}
