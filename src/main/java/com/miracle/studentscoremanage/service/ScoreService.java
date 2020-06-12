package com.miracle.studentscoremanage.service;

import com.miracle.studentscoremanage.dao.CourseRepository;
import com.miracle.studentscoremanage.dao.ScoreRepository;
import com.miracle.studentscoremanage.entity.Course;
import com.miracle.studentscoremanage.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
public class ScoreService {

    private ScoreRepository scoreRepository;
    private CourseRepository courseRepository;


    @Autowired
    public ScoreService(ScoreRepository scoreRepository, CourseRepository courseRepository) {
        this.scoreRepository = scoreRepository;
        this.courseRepository = courseRepository;
    }

    public List<Score> getScores(String type, String index, Integer start, Integer everyCount){
        List<Score> scores = null;
        if("".equals(type)){
            Course course = courseRepository.findByCourseId(index);
            if(course == null){
                return null;
            }
            scores = scoreRepository.findAllByCourseId(course.getId(), (start-1)*everyCount, everyCount);
        }else if(!"".equals(index)){
            Course course = courseRepository.findByCourseId(index);
            if(course == null){
                return null;
            }
            scores = scoreRepository.findAllByClassNameAndCourseId(type, course.getId(),(start-1)*everyCount, everyCount);
        }
        return scores;
    }

    public List<Score> getScoresStudent(Long studentId, String courseId, Integer start, Integer everyCount){
        if("".equals(courseId)){
            return scoreRepository.findAllByStudentId(studentId, start, everyCount);
        }
        Course course = courseRepository.findByCourseId(courseId);
        if(course == null){
            return null;
        }
        return Collections.singletonList(scoreRepository.findByCourseIdAndStudentId(course.getId(), studentId));
    }
    

}
