package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.UserAccountDto;
import com.codestates.preproject040.dto.request.UserRequest;
import com.codestates.preproject040.dto.response.UserResponse;
import com.codestates.preproject040.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserAccountService userAccountService;

    @GetMapping
    public List<UserResponse> getUsers() {
        return userAccountService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") String userId) {
        return userAccountService.getUser(userId);
    }

    @PatchMapping("/{userId}")
    public UserResponse updateUser(@PathVariable("userId") String userId,
                                   @RequestBody UserRequest request) {
        userAccountService.updateUser(userId, request);
        return userAccountService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        userAccountService.deleteUser(userId);
        return "회원 정보 삭제 완료";
    }

}