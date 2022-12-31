package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.UserAccountDto;
import com.codestates.preproject040.dto.request.LoginRequest;
import com.codestates.preproject040.dto.request.UserRequest;
import com.codestates.preproject040.dto.response.UserResponse;
import com.codestates.preproject040.dto.security.GoogleOAuth2Response;
import com.codestates.preproject040.dto.security.UserAccountPrincipal;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        if (userAccountRepository.findById(dto.userId()).isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_ALREADY_EXIST);

        UserAccount user = UserAccount.of(
                dto.userId(), dto.userPassword(), dto.email(), dto.nickname(), dto.userId());

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        UserAccountPrincipal principal = UserAccountPrincipal.from(UserAccountDto.from(user));
        settingSecurityContext(user, principal);

        userAccountRepository.save(user);

        return UserResponse.from(UserAccountDto.from(user));
    }

    public UserAccountDto saveUser(String username, String password, String email, String nickname) {
        UserAccount user = UserAccount.of(username, password, email, nickname, username);

        UserAccountDto dto = UserAccountDto.from(userAccountRepository.save(user));
        //settingOauth2Google(UserAccountPrincipal.from(dto));
        return dto;
    }

    public void deleteUser(String userId) {
        userAccountRepository.deleteById(userId);
    }

    public UserResponse login(LoginRequest loginRequest) {
        UserAccount user= userAccountRepository.findById(loginRequest.userId())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 없음"));

        if (!passwordEncoder.matches(loginRequest.userPassword(), user.getUserPassword()))
            throw new EntityNotFoundException("비밀번호가 맞지 않음");

        UserAccountPrincipal principal = UserAccountPrincipal.from(UserAccountDto.from(user));

        log.info("-----------------------------------");
        log.info("Principal info : {}", principal.getName());
        log.info("Principal info : {}", principal.getPassword());
        log.info("-----------------------------------");

        settingSecurityContext(user, principal);

        log.info("principal : {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return UserResponse.from(UserAccountDto.from(user));
    }

    public void settingSecurityContext(UserAccount user, UserAccountPrincipal principal) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, user.getUserPassword(), principal.authorities());
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    public void settingOauth2Google(UserAccountPrincipal principal) {
        log.info("---------------------------");
        log.info("settingOauth2Google 내부 진입!!!!!!!!!!!!!!");
        log.info("---------------------------");


        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new OAuth2AuthenticationToken(principal, principal.authorities(), "google");
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        if (SecurityContextHolder.getContext() != null) {
            log.info("SecurityContextHolder.getContext() : {}", SecurityContextHolder.getContext());
        }
        else log.warn("SecurityContextHolder.getContext() is null");
    }

}
