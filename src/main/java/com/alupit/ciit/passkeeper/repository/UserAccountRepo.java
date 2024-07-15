package com.alupit.ciit.passkeeper.repository;

import com.alupit.ciit.passkeeper.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {
}
