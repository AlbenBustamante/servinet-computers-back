package com.servinetcomputers.api.domain.auth;

import com.servinetcomputers.api.domain.auth.abs.IAuthService;
import com.servinetcomputers.api.domain.auth.dto.AuthRequest;
import com.servinetcomputers.api.domain.auth.dto.AuthResponse;
import com.servinetcomputers.api.domain.user.domain.dto.UserRequest;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
import com.servinetcomputers.api.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The authentication routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final UserRepository userService;
    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Boolean> logout(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(authService.logout(token));
    }

}


