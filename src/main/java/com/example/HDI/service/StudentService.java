package com.example.HDI.service;

import com.example.HDI.dto.request.AddStudentRequestDto;
import com.example.HDI.dto.request.StudentRequestDto;
import com.example.HDI.dto.request.TakeAndRemoveLessonRequestDto;
import com.example.HDI.dto.request.UpdateStudentRequestDto;
import com.example.HDI.dto.response.LessonResponseDto;
import com.example.HDI.dto.response.StudentResponseDto;
import com.example.HDI.entities.Lesson;
import com.example.HDI.entities.Student;
import com.example.HDI.exception.NotEnoughQuotaException;
import com.example.HDI.exception.StudentNotFoundException;
import com.example.HDI.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final LessonService lessonService;
    private final ModelMapper modelMapper;

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

    public LessonResponseDto takeALesson(TakeAndRemoveLessonRequestDto takeAndRemoveLessonRequestDto) {
        Lesson lesson = lessonService.findById(takeAndRemoveLessonRequestDto.getLessonId());
        Student student = studentRepository.findById(takeAndRemoveLessonRequestDto.getStudentId()).orElseThrow(StudentNotFoundException::new);
        if (lesson.getQuota() < 1 || student.getLesson().contains(lesson)) {
            throw new NotEnoughQuotaException();
        } else {
            lesson.setQuota(lesson.getQuota() - 1);
            student.addLesson(lesson);
        }
        studentRepository.save(student);
        lessonService.save(lesson);
        return modelMapper.map(lesson, LessonResponseDto.class);
    }

    public LessonResponseDto removeLesson(TakeAndRemoveLessonRequestDto takeAndRemoveLessonRequestDto) {
        Lesson lesson = lessonService.findById(takeAndRemoveLessonRequestDto.getLessonId());
        Student student = studentRepository.findById(takeAndRemoveLessonRequestDto.getStudentId()).orElseThrow(StudentNotFoundException::new);
        student.removeLesson(lesson);
        lesson.setQuota(lesson.getQuota() + 1);
        studentRepository.save(student);
        lessonService.save(lesson);
        return modelMapper.map(lesson, LessonResponseDto.class);
    }

    public List<LessonResponseDto> getAllLesson(StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findById(studentRequestDto.getId()).orElseThrow(StudentNotFoundException::new);
        return student.getLesson().stream().map(item -> modelMapper.map(item, LessonResponseDto.class)).toList();
    }
}
