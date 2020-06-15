package com.miracle.studentscoremanage.controller;

import com.miracle.studentscoremanage.authorization.manager.TokenManager;
import com.miracle.studentscoremanage.authorization.model.TokenModel;
import com.miracle.studentscoremanage.entity.Student;
import com.miracle.studentscoremanage.entity.Teacher;
import com.miracle.studentscoremanage.entity.User;
import com.miracle.studentscoremanage.service.StudentService;
import com.miracle.studentscoremanage.service.TeacherService;
import com.miracle.studentscoremanage.service.UserService;
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

    private TokenManager tokenManager;
    private UserService userService;

    @Autowired
    public LoginController(TokenManager tokenManager, UserService userService) {
        this.tokenManager = tokenManager;
        this.userService = userService;
    }

    @RequestMapping(value = {"/authorizations"}, method = {RequestMethod.POST})
    public UiReturn login(@RequestBody User instance, HttpServletResponse response){
        Map<String, String> map = new HashMap<>(1);
        map.put("authentication", null);
        response.setStatus(Code.HTTP_BAD_REQUEST);
        if ("".equals(instance.getName()) || "".equals(instance.getPassword())) {
            return UiReturn.notOk("用户名或密码为空", map);
        }
        User user = userService.get(instance.getName());
        if(user == null){
            return UiReturn.notOk("无此用户", map);
        }
        if(!user.getPassword().equals(instance.getPassword())){
            return UiReturn.notOk("密码错误", map);
        }
        if(user.getRole() != instance.getRole()){
            return UiReturn.notOk("用户角色错误", map);
        }
        TokenModel model = this.tokenManager.createToken(user.getId());
        String token = model.getUserId() + "_" + model.getToken();
        map.put("authentication", token);
        response.setStatus(Code.HTTP_CREATED);
        return UiReturn.ok(map);
    }
}
