package com.miracle.studentscoremanage.service;

import com.miracle.studentscoremanage.dao.CourseRepository;
import com.miracle.studentscoremanage.dao.ScoreRepository;
import com.miracle.studentscoremanage.dao.TeacherRepository;
import com.miracle.studentscoremanage.entity.Course;
import com.miracle.studentscoremanage.entity.Score;
import com.miracle.studentscoremanage.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private ScoreRepository scoreRepository;
    private CourseRepository courseRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.scoreRepository = scoreRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Score> getScores(String type, String index, Integer start, Integer everyCount){
        List<Score> scores = null;
        if("".equals(type)){
            Course course = courseRepository.findByCourseId(index);
            scores = scoreRepository.findAllByCourseId(course.getId(), (start-1)*everyCount, everyCount);
        }else{
            scores = scoreRepository.findAllByClassName(type, (start-1)*everyCount, everyCount);
        }
        return scores;
    }
}
