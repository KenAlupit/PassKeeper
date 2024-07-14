package com.alupit.ciit.passkeeper.service;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import com.alupit.ciit.passkeeper.entity.UserAccountKeys;
import com.alupit.ciit.passkeeper.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class HashingService {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserAccountKeyService userAccountKeyService;

    // Method to decode Base64-encoded strings
    public String encodeBase64(byte[] base64) {
        return Base64.getEncoder().encodeToString(base64);
    }

    // Method to decode Base64-encoded strings
    public byte[] decodeBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public byte[] generateRandomSalt() {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    public byte[] generateHash(String data , byte[] salt) throws Exception{
        // Hash the password with the salt
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);

        return md.digest(data.getBytes());
    }

    public UserInfo generateSaltHashedPassword(UserInfo userInfo) throws Exception {
        byte[] salt = generateRandomSalt();
        byte[] hashedPassword = generateHash(userInfo.getPassword(), salt);

        // Store the salt and hashedPassword in the database
        String saltBase64 = encodeBase64(salt);
        String hashedPasswordBase64 = encodeBase64(hashedPassword);

        SecretKey secretKey = encryptionService.generateSecretKey();

        userAccountKeyService.saveUserAccountKey(new UserAccountKeys(userInfo.getUsername(), secretKey));

        userInfo.setSaltBase64(saltBase64);
        userInfo.setPassword(encryptionService.encryptData(hashedPasswordBase64, secretKey));

        return userInfo;
    }
}
