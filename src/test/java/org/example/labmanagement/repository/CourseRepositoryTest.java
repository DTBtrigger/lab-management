package org.example.labmanagement.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void save() {
        Course course = Course.builder()
                .name("计算机科学与技术")
                .quantity(59)
                .semester("25-2")
                .clazz("计算机科学与技术1班")
                .type(1)
                .teacherId("01JGAKHA4H0TMVZKA20NYMZGYV")
                .build();
        courseRepository.save(course);
        log.debug(course.toString());
    }
}