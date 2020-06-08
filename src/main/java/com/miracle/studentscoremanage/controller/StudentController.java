package com.miracle.studentscoremanage.controller;

import com.miracle.studentscoremanage.authorization.manager.TokenManager;
import com.miracle.studentscoremanage.authorization.model.TokenModel;
import com.miracle.studentscoremanage.entity.Score;
import com.miracle.studentscoremanage.service.ScoreService;
import com.miracle.studentscoremanage.service.StudentService;
import com.miracle.studentscoremanage.util.Code;
import com.miracle.studentscoremanage.util.UiReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

    private ScoreService scoreService;
    private StudentService studentService;
    private TokenManager tokenManager;

    @Autowired
    public StudentController(ScoreService scoreService, StudentService studentService, TokenManager tokenManager) {
        this.scoreService = scoreService;
        this.studentService = studentService;
        this.tokenManager = tokenManager;
    }

    @GetMapping("/scores")
    public UiReturn getScores(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "Course")
    String course, @RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "pageSize", defaultValue = "10") Integer everyCount) {
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, List> map = new HashMap<>(10);
        map.put("scores", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        Long id = Long.valueOf(authentication.split("_")[0]);
        List<Score> scores = scoreService.getScoresStudent(id, course, start, everyCount);

        map.put("scores", scores);
        return UiReturn.ok(scores);
    }


    @GetMapping
    public UiReturn getInfo(HttpServletRequest request, HttpServletResponse response) {
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, Object> map = new HashMap<>(10);
        map.put("user", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        Long id = Long.valueOf(authentication.split("_")[0]);
        map.put("user", studentService.getInfo(id));
        return UiReturn.ok(map);
    }


}
