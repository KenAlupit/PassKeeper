package com.alupit.ciit.passkeeper.service;

import com.alupit.ciit.passkeeper.entity.UserInfo;
import com.alupit.ciit.passkeeper.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserInfo saveUserInfo(UserInfo userInfo){
        return userRepo.save(userInfo);
    }

    public List<UserInfo> getAllUserInfo(){
        return userRepo.findAll();
    }
}
