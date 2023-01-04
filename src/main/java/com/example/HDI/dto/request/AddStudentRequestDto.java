package com.example.HDI.dto.request;

import lombok.Data;

@Data
public class AddStudentRequestDto {
    private String name;
    private String lastName;
    private String mail;
}
