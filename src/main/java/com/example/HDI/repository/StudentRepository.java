package com.example.HDI.repository;

import com.example.HDI.entities.Lesson;
import com.example.HDI.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLesson(Lesson lesson);

}
