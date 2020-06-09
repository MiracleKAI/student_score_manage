package com.miracle.studentscoremanage.controller;

import com.miracle.studentscoremanage.authorization.manager.TokenManager;
import com.miracle.studentscoremanage.authorization.model.TokenModel;
import com.miracle.studentscoremanage.entity.Course;
import com.miracle.studentscoremanage.entity.Score;
import com.miracle.studentscoremanage.entity.Student;
import com.miracle.studentscoremanage.entity.Teacher;
import com.miracle.studentscoremanage.service.CourseService;
import com.miracle.studentscoremanage.service.ScoreService;
import com.miracle.studentscoremanage.service.StudentService;
import com.miracle.studentscoremanage.service.TeacherService;
import com.miracle.studentscoremanage.util.Code;
import com.miracle.studentscoremanage.util.UiReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@RestController
@RequestMapping("/v1/student")
public class StudentController {

    private ScoreService scoreService;
    private StudentService studentService;
    private TokenManager tokenManager;
    private CourseService courseService;
    private TeacherService teacherService;

    @Autowired
    public StudentController(ScoreService scoreService, StudentService studentService, TokenManager tokenManager, CourseService courseService, TeacherService teacherService) {
        this.scoreService = scoreService;
        this.studentService = studentService;
        this.tokenManager = tokenManager;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping("/scores")
    public UiReturn getScores(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "course")
    String course, @RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "pageSize", defaultValue = "10") Integer everyCount) {
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, List<Map<String, Object>>> map = new HashMap<>(10);
        map.put("scores", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        Long id = Long.valueOf(authentication.split("_")[0]);
        List<Score> scores = scoreService.getScoresStudent(id, course, start, everyCount);
        if (scores == null && !"".equals(course)){
            return UiReturn.notOk("无此课");
        }

        response.setStatus(Code.HTTP_OK);
        List<Map<String, Object>> results = new ArrayList<>();
        assert scores != null;
        for (Score score : scores){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("score", score);
            Course course1 = courseService.get(score.getCourseId());
            Optional<Teacher> teacher = teacherService.get(course1.getTeacherId());
            map1.put("course", course1);
            map1.put("teacher", teacher.orElse(null));
            results.add(map1);
        }
        map.put("scores", results);
        return UiReturn.ok(map);
    }


    @GetMapping
    public UiReturn getInfo(HttpServletRequest request, HttpServletResponse response) {
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, Student> map = new HashMap<>(10);
        map.put("user", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        Long id = Long.valueOf(authentication.split("_")[0]);
        Optional<Student> student = studentService.getInfo(id);
        if(student.isPresent()){
            Student student1 = student.get();
            student1.setPassword("");
            map.put("user", student1);
        }else {
            map.put("user", null);
        }
        response.setStatus(Code.HTTP_OK);
        return UiReturn.ok(map);
    }
}
