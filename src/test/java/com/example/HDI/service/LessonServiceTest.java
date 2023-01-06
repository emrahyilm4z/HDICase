
package com.example.HDI.service;

import com.example.HDI.dto.request.AddLessonRequestDto;
import com.example.HDI.dto.request.UpdateLessonRequestDto;
import com.example.HDI.dto.response.LessonResponseDto;
import com.example.HDI.dto.response.StudentResponseDto;
import com.example.HDI.entities.Lesson;
import com.example.HDI.repository.LessonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class LessonServiceTest {

    private LessonRepository lessonRepository;
    private StudentService studentService;
    private ModelMapper modelMapper;

    private LessonService lesssonService;

    @BeforeEach
    public void setUp() {

        lessonRepository = mock(LessonRepository.class);
        studentService = mock(StudentService.class);
        modelMapper = mock(ModelMapper.class);

        lesssonService = new LessonService(lessonRepository, studentService, modelMapper);

    }

    @Test
    public void addTest() {
        AddLessonRequestDto addLessonRequestDto = new AddLessonRequestDto("abc", 0);
        Lesson lesson = new Lesson(1L, "abc", 0, new HashSet<>());
        LessonResponseDto lessonResponseDto = new LessonResponseDto("abc", 0);

        Mockito.when(modelMapper.map(addLessonRequestDto, Lesson.class)).thenReturn(lesson);
        Mockito.when(lessonRepository.save(lesson)).thenReturn(lesson);
        Mockito.when(modelMapper.map(lesson, LessonResponseDto.class)).thenReturn(lessonResponseDto);

        LessonResponseDto result = lesssonService.add(addLessonRequestDto);

        assertEquals(result, lessonResponseDto);
    }

    @Test
    public void updateTest() {
        UpdateLessonRequestDto lessonRequestDto = new UpdateLessonRequestDto(1L, "abc", 0);
        Lesson lesson = new Lesson(1L, "abc", 0, new HashSet<>());
        LessonResponseDto lessonResponseDto = new LessonResponseDto("abc", 0);

        List<StudentResponseDto> studentArr = new ArrayList<>();

        Mockito.when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        Mockito.when(studentService.getStudentsTakeLesson(lesson)).thenReturn(studentArr);
        Mockito.when(lessonRepository.save(lesson)).thenReturn(lesson);
        Mockito.when(modelMapper.map(lesson, LessonResponseDto.class)).thenReturn(lessonResponseDto);

        LessonResponseDto result = lesssonService.update(lessonRequestDto);

        assertEquals(result, lessonResponseDto);

    }

    @Test
    public void getAllTest() {
        List<Lesson> arr = new ArrayList<>();
        Lesson lesson = new Lesson(1L, "abc", 0, new HashSet<>());
        arr.add(lesson);
        LessonResponseDto lessonResponseDto = new LessonResponseDto("abc", 0);
        List<LessonResponseDto> responseList = new ArrayList<>();
        responseList.add(lessonResponseDto);

        Mockito.when(lessonRepository.findAll()).thenReturn(arr);
        Mockito.when(modelMapper.map(lesson, LessonResponseDto.class)).thenReturn(lessonResponseDto);

        List<LessonResponseDto> result = lesssonService.getAll();

        assertEquals(result, responseList);

    }

    @Test
    public void findByIdTest() {
        Lesson lesson = new Lesson(1L, "abc", 0, new HashSet<>());

        Mockito.when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        Lesson result = lesssonService.findById(1L);

        assertEquals(result, lesson);

    }

}
