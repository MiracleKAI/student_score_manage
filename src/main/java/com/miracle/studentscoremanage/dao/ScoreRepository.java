package com.miracle.studentscoremanage.dao;

import com.miracle.studentscoremanage.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author miracle
 */
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    @Query(value = "SELECT * FROM course_student g WHERE g.course_id = :course_id LIMIT :start, :everyCount", nativeQuery = true)
    List<Score> findAllByCourseId(@Param("course_id") Long courseId, @Param("start") Integer start, @Param("everyCount") Integer evertCount);
    @Query(value = "SELECT * FROM course_student g WHERE g.student_id = :student_id LIMIT :start, :everyCount", nativeQuery = true)
    List<Score> findAllByStudentId(@Param("student_id") Long studentId, @Param("start") Integer start, @Param("everyCount") Integer evertCount);
    @Query(value = "SELECT * FROM course_student g WHERE g.class_name = :class_name LIMIT :start, :everyCount", nativeQuery = true)
    List<Score> findAllByClassName(@Param("class_name") String className, @Param("start") Integer start, @Param("everyCount") Integer evertCount);

    Score findByCourseIdAndStudentId(Long courseId, Long studentId);
}
