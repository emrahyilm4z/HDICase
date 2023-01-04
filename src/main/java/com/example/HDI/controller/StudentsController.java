package com.example.HDI.controller;

import com.example.HDI.dto.request.AddStudentRequestDto;
import com.example.HDI.dto.request.UpdateStudentRequestDto;
import com.example.HDI.dto.response.StudentResponseDto;
import com.example.HDI.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("student")
public class StudentsController {
    private StudentService studentService;

    @PostMapping("add")
    public ResponseEntity<StudentResponseDto> add(@RequestBody AddStudentRequestDto addStudentRequestDto) {
        return new ResponseEntity<>(studentService.add(addStudentRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<StudentResponseDto> update(@RequestBody UpdateStudentRequestDto updateStudentRequestDto) {
        return new ResponseEntity<>(studentService.update(updateStudentRequestDto), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<StudentResponseDto>> getAll() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }


}
