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

    public UserAccountKeys saveUserAccountKeys(UserAccountKeys userAccountKeys) {
        return userAccountKeyRepo.save(userAccountKeys); // Save the user account keys using the repository to the database
    }

    public List<UserAccountKeys> getAllUserAccountKeys() {
        return userAccountKeyRepo.findAll(); // Retrieve all user account keys using the repository from the database
    }
}
