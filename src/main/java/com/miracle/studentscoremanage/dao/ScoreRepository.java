package com.miracle.studentscoremanage.dao;

import com.miracle.studentscoremanage.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author miracle
 */
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {



}
