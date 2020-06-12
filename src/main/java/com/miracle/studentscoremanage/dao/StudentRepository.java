package com.miracle.studentscoremanage.dao;


import com.miracle.studentscoremanage.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author miracle
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByName(String name);

    Student findByStudentId(String studentId);

}
