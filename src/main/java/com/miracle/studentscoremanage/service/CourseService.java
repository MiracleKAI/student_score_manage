package com.miracle.studentscoremanage.service;

import com.miracle.studentscoremanage.dao.CourseRepository;
import com.miracle.studentscoremanage.entity.Course;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course get(Long courseId){
        Optional<Course> course = courseRepository.findById(courseId);
        return course.orElse(null);
    }
}
