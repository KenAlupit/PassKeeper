package com.alupit.ciit.passkeeper.service;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import com.alupit.ciit.passkeeper.entity.UserAccountKeys;
import com.alupit.ciit.passkeeper.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class HashingService {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserAccountKeyService userAccountKeyService;

    public String encodeBase64(byte[] base64) {
        return Base64.getEncoder().encodeToString(base64);
    }

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
        // Hash the data with SHA-256 and the provided salt
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);

        return md.digest(data.getBytes());
    }

    public UserAccount generateSaltHashedPassword(UserAccount userAccount) throws Exception {
        // Generate random salt and hash the password
        byte[] salt = generateRandomSalt();
        byte[] hashedPassword = generateHash(userAccount.getPassword(), salt);

        // Encode salt and hashedPassword to Base64
        String saltBase64 = encodeBase64(salt);
        String hashedPasswordBase64 = encodeBase64(hashedPassword);

        // Generate a secret key for encryption
        SecretKey secretKey = encryptionService.generateSecretKey();

        // Save the secret key associated with the user
        userAccountKeyService.saveUserAccountKeys(new UserAccountKeys(userAccount.getUsername(), secretKey));

        // Update UserInfo with salt and encrypted hashed password
        userAccount.setSaltBase64(saltBase64);
        userAccount.setPassword(encryptionService.encryptData(hashedPasswordBase64, secretKey));

        return userAccount;
    }
}
