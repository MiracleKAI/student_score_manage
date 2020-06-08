package com.miracle.studentscoremanage.service;

import com.miracle.studentscoremanage.dao.StudentRepository;
import com.miracle.studentscoremanage.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(String name){

        return studentRepository.findByName(name);
    }

}
