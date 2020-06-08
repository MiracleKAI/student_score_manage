package com.miracle.studentscoremanage.dao;

import com.miracle.studentscoremanage.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author miracle
 */
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findAllByCourseId(Long courseId);

    List<Score> findAllByStudentId(Long studentId);
}
