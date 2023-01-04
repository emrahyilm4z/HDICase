package com.example.HDI.service;

import com.example.HDI.dto.request.AddLessonRequestDto;
import com.example.HDI.dto.request.UpdateLessonRequestDto;
import com.example.HDI.dto.response.LessonResponseDto;
import com.example.HDI.entities.Lesson;
import com.example.HDI.exception.CanNotUpdateException;
import com.example.HDI.exception.LessonNotFoundException;
import com.example.HDI.repository.LessonRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LessonService {
    private LessonRepository lessonRepository;
    private StudentService studentService;
    private ModelMapper modelMapper;

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
        Lesson nowLesson = modelMapper.map(lessonRequestDto, Lesson.class);
        lessonRepository.save(nowLesson);
        return modelMapper.map(nowLesson, LessonResponseDto.class);
    }

    public List<LessonResponseDto> getAll() {
        return lessonRepository.findAll().stream().map(item -> modelMapper.map(item, LessonResponseDto.class)).toList();
    }
}
