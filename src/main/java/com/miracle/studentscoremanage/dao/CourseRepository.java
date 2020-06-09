package com.miracle.studentscoremanage.dao;

import com.miracle.studentscoremanage.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author miracle
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    List<Course> findAllByTeacherId(Long id);

    Course findByCourseId(String courseId);

}
