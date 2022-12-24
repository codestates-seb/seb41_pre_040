package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, String> {
}
