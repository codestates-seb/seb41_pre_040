package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, String> {

}
