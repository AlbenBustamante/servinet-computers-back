package com.servinetcomputers.api.domain.user.persistence.repository;

import com.servinetcomputers.api.core.util.enums.Role;
import com.servinetcomputers.api.domain.user.domain.dto.UserRequest;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
import com.servinetcomputers.api.domain.user.domain.repository.UserRepository;
import com.servinetcomputers.api.domain.user.persistence.JpaUserRepository;
import com.servinetcomputers.api.domain.user.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The user's repository implementation.
 */
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse save(UserRequest request) {
        final var entity = userMapper.toEntity(request);
        final var newUser = jpaUserRepository.save(entity);

        return userMapper.toResponse(newUser);
    }

    @Override
    public UserResponse save(UserResponse response) {
        final var entity = userMapper.toEntity(response);
        final var newUser = jpaUserRepository.save(entity);

        return userMapper.toResponse(newUser);
    }

    @Override
    public Optional<UserResponse> getLastByRole(Role role) {
        final var user = jpaUserRepository.findFirstByRoleOrderByCreatedDateDesc(role);
        return user.map(userMapper::toResponse);
    }

    @Override
    public Optional<UserResponse> getByCode(String code) {
        final var user = jpaUserRepository.findByCodeAndEnabledTrue(code);
        return user.map(userMapper::toResponse);
    }

    @Override
    public List<UserResponse> getAll() {
        return userMapper.toResponses(jpaUserRepository.findAll());
    }

    @Override
    public Optional<UserResponse> get(int userId) {
        final var user = jpaUserRepository.findByIdAndEnabledTrue(userId);
        return user.map(userMapper::toResponse);
    }
}
