package com.servinetcomputers.api.module.auth;

import com.servinetcomputers.api.module.auth.application.usecase.CreateUserUseCase;
import com.servinetcomputers.api.module.auth.application.usecase.LoginUseCase;
import com.servinetcomputers.api.module.auth.application.usecase.LogoutUseCase;
import com.servinetcomputers.api.module.auth.application.usecase.SendCodeToChangePasswordUseCase;
import com.servinetcomputers.api.module.auth.dto.AuthRequest;
import com.servinetcomputers.api.module.auth.dto.AuthResponse;
import com.servinetcomputers.api.module.auth.dto.RequestChangePasswordDto;
import com.servinetcomputers.api.module.user.domain.dto.CreateUserDto;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The authentication routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
@RestController
public class AuthController {
    private final CreateUserUseCase createUserUseCase;
    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;
    private final SendCodeToChangePasswordUseCase sendCodeToChangePasswordUseCase;

    @PostMapping(path = "/register")
    public ResponseEntity<UserDto> register(@RequestBody CreateUserDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserUseCase.call(request));
    }

    @PostMapping(path = "/request-change-password")
    public ResponseEntity<Void> requestChangePassword(@RequestBody RequestChangePasswordDto dto) {
        sendCodeToChangePasswordUseCase.call(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(loginUseCase.call(request));
    }

    @PostMapping(path = "/sign-out")
    public ResponseEntity<Boolean> logout(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(logoutUseCase.call(token));
    }
}


