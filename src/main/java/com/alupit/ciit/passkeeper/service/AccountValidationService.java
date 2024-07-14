package com.alupit.ciit.passkeeper.service;

import com.alupit.ciit.passkeeper.entity.UserAccountKeys;
import com.alupit.ciit.passkeeper.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.MessageDigest;

@Service
public class AccountValidationService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountKeyService userAccountKeyService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private HashingService hashingService;


    public boolean verifyPassword(String inputPassword, String storedHashedPasswordBase64, String storedSaltBase64) throws Exception {
        // Decode stored salt and hashed password from Base64
        byte[] storedSalt = hashingService.decodeBase64(storedSaltBase64);
        byte[] storedHashedPassword = hashingService.decodeBase64(storedHashedPasswordBase64);

        // Generate hash of input password using stored salt
        byte[] inputHashedPassword = hashingService.generateHash(inputPassword, storedSalt);

        // Compare generated hash with stored hashed password
        return MessageDigest.isEqual(storedHashedPassword, inputHashedPassword);
    }

    public boolean validateUserCredentials(String username, String password) throws Exception {
        SecretKey secretKey = null;
        for (UserInfo i : userService.getAllUserInfo()){
            for (UserAccountKeys j : userAccountKeyService.getAllUserAccountKeys()){
                if (username.equals(j.getUsername())){
                    secretKey = j.getSecretKey();
                    System.out.println(hashingService.encodeBase64(secretKey.getEncoded()));
                }
            }
            String storedPassword = encryptionService.decryptData(i.getPassword(), secretKey);
            if (username.equals(i.getUsername()) && verifyPassword(password, storedPassword, i.getSaltBase64())){
                return true;
            }
        }
        return false;
    }
}
