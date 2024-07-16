package com.alupit.ciit.passkeeper.repository;

import com.alupit.ciit.passkeeper.entity.PasswordInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordInfoRepo extends JpaRepository<PasswordInfo, Integer> {
}
