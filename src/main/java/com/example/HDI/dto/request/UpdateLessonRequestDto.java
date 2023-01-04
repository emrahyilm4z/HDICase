package com.example.HDI.dto.request;

import lombok.Data;

@Data
public class UpdateLessonRequestDto {
    private Long id;
    private String name;
    private int quota;
}
