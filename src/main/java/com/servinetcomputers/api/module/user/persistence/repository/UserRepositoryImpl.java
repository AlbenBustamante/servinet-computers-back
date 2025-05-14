package com.servinetcomputers.api.module.user.persistence.repository;

import com.servinetcomputers.api.core.util.enums.Role;
import com.servinetcomputers.api.module.user.domain.dto.CreateUserDto;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;
import com.servinetcomputers.api.module.user.domain.repository.UserRepository;
import com.servinetcomputers.api.module.user.persistence.JpaUserRepository;
import com.servinetcomputers.api.module.user.persistence.mapper.UserMapper;
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
    public UserDto save(CreateUserDto request) {
        final var entity = userMapper.toEntity(request);
        final var newUser = jpaUserRepository.save(entity);

        return userMapper.toDto(newUser);
    }

    @Override
    public UserDto save(UserDto response) {
        final var entity = userMapper.toEntity(response);
        final var newUser = jpaUserRepository.save(entity);

        return userMapper.toDto(newUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmailAndEnabledTrue(email);
    }

    @Override
    public Optional<String> getEmailByCode(String code) {
        return jpaUserRepository.findEmailByCodeAndEnabledTrue(code);
    }

    @Override
    public Optional<UserDto> getLastByRole(Role role) {
        final var user = jpaUserRepository.findFirstByRoleOrderByCreatedDateDesc(role);
        return user.map(userMapper::toDto);
    }

    @Override
    public Optional<UserDto> getByCode(String code) {
        final var user = jpaUserRepository.findByCodeAndEnabledTrue(code);
        return user.map(userMapper::toDto);
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.toDto(jpaUserRepository.findAll());
    }

    @Override
    public Optional<UserDto> get(int userId) {
        final var user = jpaUserRepository.findByIdAndEnabledTrue(userId);
        return user.map(userMapper::toDto);
    }
}
