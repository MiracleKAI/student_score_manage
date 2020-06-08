package com.miracle.studentscoremanage.dao;

import com.miracle.studentscoremanage.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author miracle
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByName(String name);


}
