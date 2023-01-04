package com.example.HDI.controller;

import com.example.HDI.dto.request.AddLessonRequestDto;
import com.example.HDI.dto.request.LessonRequestDto;
import com.example.HDI.dto.request.UpdateLessonRequestDto;
import com.example.HDI.dto.response.LessonResponseDto;
import com.example.HDI.dto.response.StudentResponseDto;
import com.example.HDI.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("lesson")
public class LessonsController {
    private LessonService lessonService;

    @PostMapping("add")
    public ResponseEntity<LessonResponseDto> add(@RequestBody AddLessonRequestDto addLessonRequestDto) {
        return new ResponseEntity<>(lessonService.add(addLessonRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<LessonResponseDto> update(@RequestBody UpdateLessonRequestDto updateLessonRequestDto) {
        return new ResponseEntity<>(lessonService.update(updateLessonRequestDto), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<LessonResponseDto>> getAll() {
        return new ResponseEntity<>(lessonService.getAll(), HttpStatus.OK);
    }

    @GetMapping("students")
    public ResponseEntity<List<StudentResponseDto>> getAllStudent(@RequestBody LessonRequestDto lessonRequestDto) {
        return new ResponseEntity<>(lessonService.getAllStudent(lessonRequestDto), HttpStatus.OK);
    }
}
