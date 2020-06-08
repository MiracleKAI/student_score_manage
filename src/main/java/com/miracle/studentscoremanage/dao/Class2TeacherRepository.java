package com.miracle.studentscoremanage.dao;


import com.miracle.studentscoremanage.entity.Class2Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Class2TeacherRepository extends JpaRepository<Class2Teacher, Long> {


    List<Class2Teacher> findAllByTeacherId(Long teacherId);

}
