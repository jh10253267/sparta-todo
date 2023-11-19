package com.sparta.spartatodo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDTO {
    private String title;
    private String content;
    private String writer;
    private boolean complete;
    private LocalDateTime createdAt;
}
