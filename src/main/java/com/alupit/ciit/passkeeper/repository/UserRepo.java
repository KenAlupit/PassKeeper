package com.alupit.ciit.passkeeper.repository;

import com.alupit.ciit.passkeeper.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserInfo, Integer> {
}
