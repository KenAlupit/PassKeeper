package com.alupit.ciit.passkeeper.service;

import com.alupit.ciit.passkeeper.entity.PasswordInfo;
import com.alupit.ciit.passkeeper.repository.PasswordInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasswordInfoService {

    @Autowired
    PasswordInfoRepo passwordInfoRepo;

    public PasswordInfo savePasswordInfo(PasswordInfo passwordInfo) {
        return passwordInfoRepo.save(passwordInfo); // Save the passwords using the repository to the database
    }

    public List<PasswordInfo> getAllPasswordInfo() {
        return passwordInfoRepo.findAll(); // Retrieve all passwords using the repository from the database
    }
    public PasswordInfo getPasswordInfobyid(int id) {
        return passwordInfoRepo.findById(id).orElse(null); // Retrieve all passwords related to the id
    }
    public void deletePasswordInfo(int id) {
        passwordInfoRepo.deleteById(id);
    }
}
