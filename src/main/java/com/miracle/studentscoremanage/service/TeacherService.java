package com.miracle.studentscoremanage.service;

import com.miracle.studentscoremanage.dao.*;
import com.miracle.studentscoremanage.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;
    private Class2TeacherRepository class2TeacherRepository;
    private ClassRepository classRepository;
    private ScoreRepository scoreRepository;
    private CourseRepository courseRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, Class2TeacherRepository class2TeacherRepository, ClassRepository classRepository, ScoreRepository scoreRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.class2TeacherRepository = class2TeacherRepository;
        this.classRepository = classRepository;
        this.scoreRepository = scoreRepository;
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses(Long teacherId){
        return courseRepository.findAllByTeacherId(teacherId);
    }

    public Teacher getTeacher(String name){
        return teacherRepository.findByTeacherId(name);
    }

    public Optional<Teacher> get(Long id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher;
    }
    public List<Score> getScoresByCourseId(String courseId){
        return null;
    }

    public List<Classroom> getClasses(Long id){
        List<Class2Teacher> class2Teachers = class2TeacherRepository.findAllByTeacherId(id);
        List<Classroom> classrooms = new ArrayList<>();
        for(Class2Teacher class2Teacher : class2Teachers){
            Long classId = class2Teacher.getClassId();
            Optional<Classroom> classroom = classRepository.findById(classId);
            classrooms.add(classroom.orElse(null));
        }
        return classrooms;
    }

    public List<Score> save(List<Score> scores){
        for (Score score: scores){
            scoreRepository.save(score);
        }
        return scores;
    }

    public List<Optional<Score>> update(List<Score> scores){
        List<Optional<Score>> optionalList = new ArrayList<>();
        for (Score score: scores){
            Optional<Score> origin = scoreRepository.findById(score.getId());
            if(origin.isPresent()){
                Score score1 = origin.get();
                score1.setClassName(score.getClassName());
                score1.setStudentId(score.getStudentId());
                score1.setCourseId(score.getCourseId());
                score1.setFinalScore(score.getFinalScore());
                score1.setFlag(score.getFlag());
                score1.setNormalScore(score.getNormalScore());
                score1.setScore(score.getScore());
                scoreRepository.save(score1);
                optionalList.add(scoreRepository.findById(score.getId()));
            }
        }
        return optionalList;
    }

    public void delete(Long id){
        scoreRepository.deleteById(id);
    }
}
