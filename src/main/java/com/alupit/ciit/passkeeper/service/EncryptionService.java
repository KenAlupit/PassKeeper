package com.alupit.ciit.passkeeper.service;

import javax.crypto.*;
import javax.crypto.KeyGenerator;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private static final String ALGORITHM = "AES";

    public SecretKey generateSecretKey() throws Exception {
        // Create a KeyGenerator for AES
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);

        // Generate a 128-bit key (16 bytes)
        keyGen.init(128);

        return keyGen.generateKey();
    }

    public String encryptData(String data, SecretKey secretKey) throws Exception {
        // Encrypt the hashed password
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());

        // Encode encryptedPassword to Base64
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public String decryptData(String encryptedData, SecretKey secretKey) throws Exception {
        // Convert encryptedData from Base64 to byte array
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        // Initialize AES Cipher for decryption
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the encrypted data
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Convert decrypted byte array to String (if applicable)
        String decryptedData = new String(decryptedBytes);

        return decryptedData;
    }
}
