package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.UserAccountDto;
import com.codestates.preproject040.dto.request.UserRequest;
import com.codestates.preproject040.dto.response.UserResponse;
import com.codestates.preproject040.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> findUser(String userId) {
        return userAccountRepository.findById(userId)
                .map(UserAccountDto::from);
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(String userId) {
        return userAccountRepository.findById(userId)
                .map(UserAccountDto::from)
                .map(UserResponse::from)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없음"));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userAccountRepository.findAll().stream()
                .map(UserAccountDto::from)
                .map(UserResponse::from)
                .toList();
    }

    public void updateUser(String userId, UserRequest request) {
        try {
            UserAccount user = userAccountRepository.getReferenceById(userId);

            if (request.userPassword() != null) user.setUserPassword(passwordEncoder.encode(request.userPassword()));
            if (request.email() != null) user.setEmail(request.email());
            if (request.nickname() != null) user.setNickname(request.nickname());
            userAccountRepository.save(user);
        } catch (EntityNotFoundException e) {
            log.warn("유저 업데이트 실페. 유저 계정 수정에 필요한 정보를 찾을 수 없음 - {}", e.getLocalizedMessage());
        }
    }

    public UserResponse saveUser(UserAccountDto dto) {
        UserAccount user = UserAccount.of(
                dto.userId(), dto.userPassword(), dto.email(), dto.nickname(), dto.userId());

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        userAccountRepository.save(user);

        return UserResponse.from(UserAccountDto.from(user));
    }

    public UserAccountDto saveUser(String username, String password, String email, String nickname) {
        UserAccount user = UserAccount.of(username, password, email, nickname, username);
        return UserAccountDto.from(userAccountRepository.save(user));
    }

    public void deleteUser(String userId) {
        userAccountRepository.deleteById(userId);
    }
}
