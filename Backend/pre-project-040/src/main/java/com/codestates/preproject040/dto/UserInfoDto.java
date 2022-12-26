package com.codestates.preproject040.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoDto {
    private String userId;
    private String userPassword;
    private String email;
    private String nickname;

    private UserInfoDto(String userId, String userPassword, String email, String nickname) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserInfoDto of(String username, String userPassword, String email, String nickname) {
        return new UserInfoDto(username, userPassword, email, nickname);
    }

    public static UserInfoDto from(UserAccountDto dto) {
        return new UserInfoDto(
                dto.userId(),
                dto.userPassword(),
                dto.email(),
                dto.nickname()
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                userId,
                userPassword,
                email,
                nickname
        );
    }

}
