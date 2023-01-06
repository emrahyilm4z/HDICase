package com.example.HDI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLessonRequestDto {
    private Long id;
    private String name;
    private int quota;
}
