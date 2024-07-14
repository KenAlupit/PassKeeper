package com.alupit.ciit.passkeeper.repository;

import com.alupit.ciit.passkeeper.entity.UserAccountKeys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountKeyRepo extends JpaRepository<UserAccountKeys, Integer> {
}
