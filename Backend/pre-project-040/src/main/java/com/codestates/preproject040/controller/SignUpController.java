package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.UserAccountDto;
import com.codestates.preproject040.dto.UserInfoDto;
import com.codestates.preproject040.dto.response.UserResponse;
import com.codestates.preproject040.service.UserAccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final UserAccountService userAccountService;

    @GetMapping("/joinForm")
    public String joinForm(){
        return "join";
    }

    @PostMapping("/join")
    public UserResponse registerUser(@RequestBody UserInfoDto request) {

        UserAccountDto dto = request.toDto();

        return userAccountService.saveUser(dto);
    }

}
