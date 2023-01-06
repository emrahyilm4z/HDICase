package com.example.HDI.repository;

import com.example.HDI.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
