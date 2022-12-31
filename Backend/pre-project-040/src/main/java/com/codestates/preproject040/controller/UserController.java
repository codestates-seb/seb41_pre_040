package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.request.LoginRequest;
import com.codestates.preproject040.dto.request.UserRequest;
import com.codestates.preproject040.dto.response.UserResponse;
import com.codestates.preproject040.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserAccountService userAccountService;

    @PostMapping("/login")
    private UserResponse login(@RequestBody LoginRequest loginRequest) {
        return userAccountService.login(loginRequest);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        return new ResponseEntity(HttpStatus.OK);
    }

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