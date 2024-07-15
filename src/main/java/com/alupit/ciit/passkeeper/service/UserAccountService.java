package com.alupit.ciit.passkeeper.service;

import com.alupit.ciit.passkeeper.entity.UserAccount;
import com.alupit.ciit.passkeeper.repository.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    public UserAccount saveUserInfo(UserAccount userAccount){
        return userAccountRepo.save(userAccount); // Save the user information using the repository to the database
    }

    public List<UserAccount> getAllUserInfo(){
        return userAccountRepo.findAll(); // Retrieve all user information using the repository from the database
    }
}
