package com.alupit.ciit.passkeeper.entity;

import jakarta.persistence.*;

import javax.crypto.SecretKey;

@Entity
@Table(name = "user_password_keys")
public class PasswordInfoKeys {

    @Id
    @Column(name = "key_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keyId; // Primary key column, auto-generated by the database

    @Column(name = "password_info")
    private String passwordOwner; // Column to store the username of the corresponding owner associated with the stored password

    @Column(name = "name")
    private String name; // Column to store the corresponding name associated with the stored password

    @Column(name = "secret_key")
    private SecretKey secretKey; // Column to store the secret key (using javax.crypto.SecretKey)

    // Default constructor (required by JPA)
    public PasswordInfoKeys() {
    }

    // Constructor with parameters for username and secretKey
    public PasswordInfoKeys(String passwordOwner, SecretKey secretKey) {
        this.passwordOwner = passwordOwner;
        this.secretKey = secretKey;
    }

    // Getter for keyId
    public int getKeyId() {
        return keyId;
    }

    // Setter for keyId
    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    // Getter for username
    public String getPasswordOwner() {
        return passwordOwner;
    }

    // Setter for username
    public void setPasswordOwner(String username) {
        this.passwordOwner = username;
    }

    // Getter for secretKey
    public SecretKey getSecretKey() {
        return secretKey;
    }

    // Setter for secretKey
    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
}
