package com.alupit.ciit.passkeeper.service;

import com.alupit.ciit.passkeeper.entity.PasswordInfoKeys;
import com.alupit.ciit.passkeeper.repository.PasswordInfoKeyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordInfoKeyService {

    @Autowired
    PasswordInfoKeyRepo passwordInfoKeyRepo;

    public PasswordInfoKeys savePasswordInfoKeys(PasswordInfoKeys passwordInfoKeys) {
        return passwordInfoKeyRepo.save(passwordInfoKeys); // Save the password keys using the repository to the database
    }

    public List<PasswordInfoKeys> getAllPasswordInfoKeys() {
        return passwordInfoKeyRepo.findAll(); // Retrieve all password keys using the repository from the database
    }
    public void deletePasswordInfoKey(int id) {
        passwordInfoKeyRepo.deleteById(id);
    }
}
