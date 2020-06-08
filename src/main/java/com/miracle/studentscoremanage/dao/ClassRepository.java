package com.miracle.studentscoremanage.dao;

import com.miracle.studentscoremanage.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Classroom, Long> {
}
