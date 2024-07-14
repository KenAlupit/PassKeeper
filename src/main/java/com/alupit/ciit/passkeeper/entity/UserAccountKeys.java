package com.alupit.ciit.passkeeper.entity;

import jakarta.persistence.*;

import javax.crypto.SecretKey;

@Entity
@Table(name = "user_account_keys")
public class UserAccountKeys {

    @Id
    @Column(name = "key_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keyId;

    @Column(name = "username")
    private String username;

    @Column(name = "secret_key")
    private SecretKey secretKey;

    public UserAccountKeys() {
    }

    public UserAccountKeys(String username, SecretKey secretKey) {
        this.username = username;
        this.secretKey = secretKey;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
}
