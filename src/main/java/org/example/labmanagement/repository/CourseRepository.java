package org.example.labmanagement.repository;

import org.example.labmanagement.dox.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,String> {
    List<Course> findByTeacherIdAndSemester(String teacherId, String semester);

}
