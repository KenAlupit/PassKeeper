package com.alupit.ciit.passkeeper.repository;

import com.alupit.ciit.passkeeper.entity.PasswordInfoKeys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordInfoKeyRepo extends JpaRepository<PasswordInfoKeys, Integer> {
}
