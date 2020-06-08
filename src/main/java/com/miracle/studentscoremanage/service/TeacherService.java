package com.miracle.studentscoremanage.service;

import com.miracle.studentscoremanage.dao.TeacherRepository;
import com.miracle.studentscoremanage.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher getTeacher(String name){
        return teacherRepository.findByName(name);
    }
}
