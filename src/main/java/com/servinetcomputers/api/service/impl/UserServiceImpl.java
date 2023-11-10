package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.UserRequest;
import com.servinetcomputers.api.dto.response.DataResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.dto.response.UserResponse;
import com.servinetcomputers.api.exception.PasswordsDoNotMatchException;
import com.servinetcomputers.api.exception.UserAlreadyExistsException;
import com.servinetcomputers.api.exception.UserNotFoundException;
import com.servinetcomputers.api.exception.UserUnavailableException;
import com.servinetcomputers.api.mapper.UserMapper;
import com.servinetcomputers.api.repository.UserRepository;
import com.servinetcomputers.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The user's service implementation.
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public PageResponse<UserResponse> create(UserRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("email");
        }

        if (!request.passwordsMatch()) {
            throw new PasswordsDoNotMatchException();
        }

        final var entity = mapper.toEntity(request);

        entity.setPassword("encode:" + request.password());

        final var response = mapper.toResponse(repository.save(entity));

        return new PageResponse<>(201, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Override
    public PageResponse<UserResponse> update(int userId, UserRequest request) {
        final var userFound = repository.findById(userId);

        if (userFound.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        final var user = userFound.get();

        if (Boolean.FALSE.equals(user.getIsAvailable())) {
            throw new UserUnavailableException(userId);
        }

        user.setName(request.name() == null ? user.getName() : request.name());
        user.setLastName(request.lastName() == null ? user.getLastName() : request.lastName());
        user.setEmail(request.email() == null ? user.getEmail() : request.email());

        final var response = mapper.toResponse(repository.save(user));

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
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
