package com.miracle.studentscoremanage.service;

import com.miracle.studentscoremanage.dao.UserRepository;
import com.miracle.studentscoremanage.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(String userName){
        return userRepository.findByName(userName);
    }

    public String getNameById(Long id){
        return userRepository.findNameById(id);
    }
}
