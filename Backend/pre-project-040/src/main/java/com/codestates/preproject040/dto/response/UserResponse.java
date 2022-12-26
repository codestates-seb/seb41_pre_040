package com.codestates.preproject040.dto.response;

import com.codestates.preproject040.dto.UserAccountDto;

public record UserResponse(
        String userId,
        String userPassword,
        String email,
        String nickname
) {
    public static UserResponse from(UserAccountDto dto) {
        return new UserResponse(dto.userId(), dto.userPassword(), dto.email(), dto.nickname());
    }

}
