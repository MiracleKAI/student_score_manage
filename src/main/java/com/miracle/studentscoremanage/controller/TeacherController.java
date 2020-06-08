package com.miracle.studentscoremanage.controller;

import com.miracle.studentscoremanage.authorization.manager.TokenManager;
import com.miracle.studentscoremanage.authorization.model.TokenModel;
import com.miracle.studentscoremanage.entity.Score;
import com.miracle.studentscoremanage.service.ScoreService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/teacher")
public class TeacherController {


    private TeacherService teacherService;
    private ScoreService scoreService;
    private TokenManager tokenManager;

    @Autowired
    public TeacherController(TeacherService teacherService, ScoreService scoreService, TokenManager tokenManager) {
        this.teacherService = teacherService;
        this.scoreService = scoreService;
        this.tokenManager = tokenManager;
    }

    @GetMapping("/scores")
    public UiReturn getScores(HttpServletRequest request, @RequestParam("type") String type, @RequestParam("index")String index, @RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "pageSize", defaultValue = "10") Integer everyCount, HttpServletResponse response){
        String authentication = request.getHeader("authentication");
        TokenModel model = tokenManager.getToken(authentication);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        Map<String, List> map = new HashMap<>(10);
        map.put("scores", null);
        if (!tokenManager.checkToken(model)) {
            return UiReturn.notOk("用户已注销", map);
        }
        List<Score> scores = scoreService.getScores(type, index, start, everyCount);
        if (scores == null){
            return UiReturn.notOk("无数据", map);
        }
        response.setStatus(Code.HTTP_OK);

        map.put("scores", scores);
        return UiReturn.ok(map);
    }


}
