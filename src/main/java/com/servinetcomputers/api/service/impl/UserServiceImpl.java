package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.UserRequest;
import com.servinetcomputers.api.dto.response.DataResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.dto.response.UserResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.PasswordsDoNotMatchException;
import com.servinetcomputers.api.exception.UserAlreadyExistsException;
import com.servinetcomputers.api.exception.UserNotFoundException;
import com.servinetcomputers.api.exception.UserUnavailableException;
import com.servinetcomputers.api.mapper.UserMapper;
import com.servinetcomputers.api.repository.UserRepository;
import com.servinetcomputers.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.util.constants.SecurityConstants.USER_AUTHORITY;

/**
 * The user's service implementation.
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PageResponse<UserResponse> create(UserRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("email");
        }

        if (!request.passwordsMatch()) {
            throw new PasswordsDoNotMatchException();
        }

        final var entity = mapper.toEntity(request);

        entity.setPassword(passwordEncoder.encode(request.password()));

        final var response = mapper.toResponse(repository.save(entity));

        return new PageResponse<>(201, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Override
    public PageResponse<UserResponse> get(int userId) {
        final var user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.getIsAvailable().equals(Boolean.FALSE)) {
            throw new UserUnavailableException(userId);
        }

        final var response = mapper.toResponse(user);

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = USER_AUTHORITY)
    @Override
    public PageResponse<UserResponse> update(int userId, UserRequest request) {
        final var user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.getIsAvailable().equals(Boolean.FALSE)) {
            throw new UserUnavailableException(userId);
        }

        user.setName(request.name() == null ? user.getName() : request.name());
        user.setLastName(request.lastName() == null ? user.getLastName() : request.lastName());
        user.setEmail(request.email() == null ? user.getEmail() : request.email());

        final var response = mapper.toResponse(repository.save(user));

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Secured(value = USER_AUTHORITY)
    @Override
    public boolean delete(int userId) {
        final var userFound = repository.findById(userId);

        if (userFound.isEmpty()) {
            return false;
        }

        userFound.get().setIsAvailable(false);

        repository.save(userFound.get());

        return true;
    }

}
