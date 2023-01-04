package com.example.HDI.controller;

import com.example.HDI.dto.request.AddStudentRequestDto;
import com.example.HDI.dto.request.StudentRequestDto;
import com.example.HDI.dto.request.TakeAndRemoveLessonRequestDto;
import com.example.HDI.dto.request.UpdateStudentRequestDto;
import com.example.HDI.dto.response.LessonResponseDto;
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

    @PostMapping("take")
    public ResponseEntity<LessonResponseDto> take(@RequestBody TakeAndRemoveLessonRequestDto takeAndRemoveLessonRequestDto) {
        return new ResponseEntity<>(studentService.takeALesson(takeAndRemoveLessonRequestDto), HttpStatus.OK);
    }

    @PostMapping("remove")
    public ResponseEntity<LessonResponseDto> remove(@RequestBody TakeAndRemoveLessonRequestDto takeAndRemoveLessonRequestDto) {
        return new ResponseEntity<>(studentService.removeLesson(takeAndRemoveLessonRequestDto), HttpStatus.OK);
    }

    @GetMapping("lessons")
    public ResponseEntity<List<LessonResponseDto>> getAllLesson(@RequestBody StudentRequestDto studentRequestDto){
        return new ResponseEntity<>(studentService.getAllLesson(studentRequestDto), HttpStatus.OK);
    }
}
