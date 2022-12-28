package com.codestates.preproject040.dto;

import com.codestates.preproject040.domain.UserAccount;

import java.time.LocalDateTime;

public record UserAccountDto (
        String userId,
        String userPassword,
        String email,
        String nickname,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserAccountDto of(String userId, String userPassword, String email, String nickname) {
        return new UserAccountDto(userId, userPassword, email, nickname, null, userId, null, null);
    }

    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(userId, userPassword, email, nickname, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                email,
                nickname
        );
    }

}

//#백엔드-채팅에 메시지 보내기
