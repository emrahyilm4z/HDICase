package com.example.HDI.dto.request;

import lombok.Data;

@Data
public class TakeAndRemoveLessonRequestDto {
    private Long studentId;
    private Long lessonId;
}
