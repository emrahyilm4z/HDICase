package com.example.HDI.dto.request;

import lombok.Data;

@Data
public class UpdateStudentRequestDto {
    private Long id;
    private String name;
    private String lastName;
    private String mail;
}
