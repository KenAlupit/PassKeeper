package com.alupit.ciit.passkeeper.service;

import com.alupit.ciit.passkeeper.entity.PasswordInfo;
import com.alupit.ciit.passkeeper.repository.PasswordInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordInfoService {

    @Autowired
    PasswordInfoRepo passwordInfoRepo;

    public PasswordInfo savePasswordInfo(PasswordInfo passwordInfo) {
        return passwordInfoRepo.save(passwordInfo); // Save the passwords using the repository to the database
    }

    public List<PasswordInfo> getAllUserAccountKeys() {
        return passwordInfoRepo.findAll(); // Retrieve all passwords using the repository from the database
    }
}
