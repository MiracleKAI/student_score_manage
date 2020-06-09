package com.miracle.studentscoremanage.controller;

import com.miracle.studentscoremanage.authorization.manager.TokenManager;
import com.miracle.studentscoremanage.authorization.model.TokenModel;
import com.miracle.studentscoremanage.entity.Student;
import com.miracle.studentscoremanage.entity.Teacher;
import com.miracle.studentscoremanage.entity.User;
import com.miracle.studentscoremanage.service.StudentService;
import com.miracle.studentscoremanage.service.TeacherService;
import com.miracle.studentscoremanage.util.Code;
import com.miracle.studentscoremanage.util.UiReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/v1"})
public class LoginController {

    private TeacherService teacherService;
    private StudentService studentService;
    private TokenManager tokenManager;


    @Autowired
    public LoginController(TeacherService teacherService, StudentService studentService, TokenManager tokenManager) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.tokenManager = tokenManager;
    }

    @RequestMapping(value = {"/authorizations"}, method = {RequestMethod.POST})
    public UiReturn login(@RequestBody User instance, HttpServletResponse response){
        Map<String, String> map = new HashMap<>(1);
        map.put("authentication", null);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        long id = -1;
        if (instance.getName() == null || instance.getPassword() == null) {
            return UiReturn.notOk("用户名或密码为空", map);
        }

        if (instance.getRole() == 1){
            Teacher teacher = teacherService.getTeacher(instance.getName());
            if (teacher == null){
                return UiReturn.notOk("无此用户", map);
            }
            if (!teacher.getPassword().equals(instance.getPassword())) {
                return UiReturn.notOk("密码错误", map);
            }
            id = teacher.getId();
        }else {
            Student student = studentService.getStudent(instance.getName());
            if (student == null){
                return UiReturn.notOk("无此用户", map);
            }
            if (!student.getPassword().equals(instance.getPassword())) {
                return UiReturn.notOk("密码错误", map);
            }
            id = student.getId();
        }
        TokenModel model = this.tokenManager.createToken(id);
        String token = model.getUserId() + "_" + model.getToken();
        map.put("authentication", token);
        response.setStatus(Code.HTTP_CREATED);
        return UiReturn.ok(map);
    }


}
