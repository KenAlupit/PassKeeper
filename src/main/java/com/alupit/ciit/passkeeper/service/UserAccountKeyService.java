package com.alupit.ciit.passkeeper.service;

import com.alupit.ciit.passkeeper.entity.UserAccountKeys;
import com.alupit.ciit.passkeeper.repository.UserAccountKeyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountKeyService {

    @Autowired
    private UserAccountKeyRepo userAccountKeyRepo;

    public UserAccountKeys saveUserAccountKey(UserAccountKeys userAccountKeys) {
        return userAccountKeyRepo.save(userAccountKeys);
    }

    public List<UserAccountKeys> getAllUserAccountKeys(){
        return userAccountKeyRepo.findAll();
    }

}
