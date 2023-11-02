package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.UserRequest;
import com.servinetcomputers.api.dto.response.UserResponse;
import com.servinetcomputers.api.mapper.UserMapper;
import com.servinetcomputers.api.repository.UserRepository;
import com.servinetcomputers.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The user's service implementation.
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserResponse create(UserRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new RuntimeException("This email already exists.");
        }

        if (!request.passwordsMatch()) {
            throw new RuntimeException("The passwords don't match!");
        }

        final var entity = mapper.toEntity(request);

        entity.setPassword("encode:" + request.password());

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public Optional<UserResponse> update(int userId, UserRequest request) {
        final var userFound = repository.findById(userId);

        if (userFound.isEmpty()) {
            return Optional.empty();
        }

        final var user = userFound.get();

        if (!user.getIsAvailable()) {
            throw new RuntimeException("The user is unavailable");
        }

        user.setName(request.name() == null ? user.getName() : request.name());
        user.setLastName(request.lastName() == null ? user.getLastName() : request.lastName());
        user.setEmail(request.email() == null ? user.getEmail() : request.email());

        return Optional.of(mapper.toResponse(repository.save(user)));
    }

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
