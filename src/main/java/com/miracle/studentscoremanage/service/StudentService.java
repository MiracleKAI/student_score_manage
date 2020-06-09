package com.miracle.studentscoremanage.service;

import com.miracle.studentscoremanage.dao.StudentRepository;
import com.miracle.studentscoremanage.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(String name){

        return studentRepository.findByStudentId(name);
    }

    public Optional<Student> getInfo(Long id){
        return studentRepository.findById(id);
    }

}
