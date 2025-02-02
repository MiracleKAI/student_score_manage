package com.miracle.studentscoremanage.controller;

import com.miracle.studentscoremanage.authorization.manager.TokenManager;
import com.miracle.studentscoremanage.authorization.model.TokenModel;
import com.miracle.studentscoremanage.entity.*;
import com.miracle.studentscoremanage.service.ScoreService;
import com.miracle.studentscoremanage.service.StudentService;
import com.miracle.studentscoremanage.service.TeacherService;
import com.miracle.studentscoremanage.service.UserService;
import com.miracle.studentscoremanage.util.Code;
import com.miracle.studentscoremanage.util.UiReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/v1/teacher")
public class TeacherController {


    private TeacherService teacherService;
    private ScoreService scoreService;
    private TokenManager tokenManager;
    private StudentService studentService;
    private UserService userService;

    @Autowired
    public TeacherController(TeacherService teacherService, ScoreService scoreService, TokenManager tokenManager, StudentService studentService, UserService userService) {
        this.teacherService = teacherService;
        this.scoreService = scoreService;
        this.tokenManager = tokenManager;
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping
    public UiReturn getOneself(HttpServletRequest request, HttpServletResponse response){

        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, Teacher> map = new HashMap<>(10);
        map.put("user", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        Long id = model.getUserId();
        String userName = userService.getNameById(id);
        Teacher teacher = teacherService.getTeacher(userName);
        map.put("user", teacher);
        response.setStatus(Code.HTTP_OK);
        return UiReturn.ok(map);
    }

    @GetMapping("/scores")
    public UiReturn getScores(HttpServletRequest request, @RequestParam("class") String clazz, @RequestParam("course")String index, @RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "pageSize", defaultValue = "10") Integer everyCount, HttpServletResponse response){
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, List> map = new HashMap<>(10);
        map.put("scores", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        List<Score> scores = scoreService.getScores(clazz, index, start, everyCount);

        if (scores == null){
            return UiReturn.notOk("无数据", map);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for(Score score : scores){
            Optional<Student> student = studentService.getInfo(score.getStudentId());
            Map<String, Object> map1  = new HashMap<>();
            map1.put("score", score);
            Student student1 = null;
            if (student.isPresent()){
                student1 = student.get();
            }
            map1.put("student", student1);
            list.add(map1);
        }
        response.setStatus(Code.HTTP_OK);
        map.put("scores", list);
        return UiReturn.ok(map);
    }

    @PostMapping("/scores")
    public UiReturn saveScore(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Score> scores){
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, List> map = new HashMap<>(10);
        map.put("scores", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        List<Score> list = teacherService.save(scores);
        response.setStatus(Code.HTTP_OK);
        map.put("scores", list);
        return UiReturn.ok(map);
    }

    @PatchMapping("/scores")
    public UiReturn updateScore(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Score> scores){
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, List> map = new HashMap<>(10);
        map.put("scores", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        List<Optional<Score>> optionalList = teacherService.update(scores);
        response.setStatus(Code.HTTP_OK);
        map.put("scores", optionalList);
        return UiReturn.ok(map);
    }

    @DeleteMapping("/scores")
    public UiReturn deleteScore(HttpServletRequest request, HttpServletResponse response, @RequestParam("cid") Long id){
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销");
        }

        teacherService.delete(id);
        response.setStatus(Code.HTTP_OK);
        return UiReturn.ok(null);
    }

    @GetMapping("/class")
    public UiReturn getClasses(HttpServletRequest request, HttpServletResponse response){
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, List> map = new HashMap<>(10);
        map.put("classes", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        Long id = model.getUserId();
        String userName = userService.getNameById(id);
        Teacher teacher = teacherService.getTeacher(userName);
        List<Classroom> classrooms = teacherService.getClasses(teacher.getId());
        response.setStatus(Code.HTTP_OK);
        map.put("classes", classrooms);
        return UiReturn.ok(map);
    }

    @GetMapping("/course")
    public UiReturn getCourse(HttpServletRequest request, HttpServletResponse response){
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, List> map = new HashMap<>(10);
        map.put("courses", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        Long id = model.getUserId();
        String userName = userService.getNameById(id);
        Teacher teacher = teacherService.getTeacher(userName);
        List<Course> courses = teacherService.getCourses(teacher.getId());
        response.setStatus(Code.HTTP_OK);
        map.put("courses", courses);
        return UiReturn.ok(map);
    }
}
