package com.example.HDI.service;

import com.example.HDI.dto.request.AddLessonRequestDto;
import com.example.HDI.dto.request.LessonRequestDto;
import com.example.HDI.dto.request.UpdateLessonRequestDto;
import com.example.HDI.dto.response.LessonResponseDto;
import com.example.HDI.dto.response.StudentResponseDto;
import com.example.HDI.entities.Lesson;
import com.example.HDI.exception.CanNotUpdateException;
import com.example.HDI.exception.LessonNotFoundException;
import com.example.HDI.repository.LessonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public LessonService(LessonRepository lessonRepository, @Lazy StudentService studentService, ModelMapper modelMapper) {
        this.lessonRepository = lessonRepository;
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    public LessonResponseDto add(AddLessonRequestDto addLessonRequestDto) {
        Lesson lesson = modelMapper.map(addLessonRequestDto, Lesson.class);
        lessonRepository.save(lesson);
        return modelMapper.map(lesson, LessonResponseDto.class);
    }

    public LessonResponseDto update(UpdateLessonRequestDto lessonRequestDto) {
        Lesson lesson = lessonRepository.findById(lessonRequestDto.getId()).orElseThrow(LessonNotFoundException::new);
        if (studentService.getStudentsTakeLesson(lesson).size() > lessonRequestDto.getQuota()) {
            throw new CanNotUpdateException();
        }
        lesson.setQuota(lessonRequestDto.getQuota());
        lesson.setName(lessonRequestDto.getName());
        lessonRepository.save(lesson);
        return modelMapper.map(lesson, LessonResponseDto.class);
    }

    public List<LessonResponseDto> getAll() {
        return lessonRepository.findAll().stream().map(item -> modelMapper.map(item, LessonResponseDto.class)).toList();
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow(LessonNotFoundException::new);
    }

    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public List<StudentResponseDto> getAllStudent(LessonRequestDto lessonRequestDto) {
        Lesson lesson = lessonRepository.findById(lessonRequestDto.getId()).orElseThrow(LessonNotFoundException::new);
        return studentService.getStudentsTakeLesson(lesson);
    }
}
