package com.example.HDI.service;

import com.example.HDI.dto.request.AddStudentRequestDto;
import com.example.HDI.dto.request.UpdateStudentRequestDto;
import com.example.HDI.dto.response.StudentResponseDto;
import com.example.HDI.entities.Lesson;
import com.example.HDI.entities.Student;
import com.example.HDI.exception.StudentNotFoundException;
import com.example.HDI.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public StudentResponseDto add(AddStudentRequestDto addStudentRequestDto) {
        Student student = modelMapper.map(addStudentRequestDto, Student.class);
        studentRepository.save(student);
        return modelMapper.map(student, StudentResponseDto.class);
    }

    public StudentResponseDto update(UpdateStudentRequestDto updateStudentRequestDto) {
        studentRepository.findById(updateStudentRequestDto.getId()).orElseThrow(StudentNotFoundException::new);
        Student student = modelMapper.map(updateStudentRequestDto, Student.class);
        studentRepository.save(student);
        return modelMapper.map(student, StudentResponseDto.class);
    }

    public List<StudentResponseDto> getAll() {
        return studentRepository.findAll().stream().map(item -> modelMapper.map(item, StudentResponseDto.class)).toList();
    }

    public List<StudentResponseDto> getStudentsTakeLesson(Lesson lesson) {
        return studentRepository.findByLesson(lesson).stream().map(item -> modelMapper.map(item, StudentResponseDto.class)).toList();
    }

}
