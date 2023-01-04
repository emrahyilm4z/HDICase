package com.example.HDI.repository;

import com.example.HDI.entities.Lesson;
import com.example.HDI.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByStudent(Student student);
}
