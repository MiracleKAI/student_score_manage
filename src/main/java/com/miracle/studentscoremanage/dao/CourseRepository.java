package com.miracle.studentscoremanage.dao;

import com.miracle.studentscoremanage.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author miracle
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


}
