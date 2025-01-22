package com.servinetcomputers.api.domain.user.persistence.repository;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.user.domain.dto.UserRequest;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
import com.servinetcomputers.api.domain.user.domain.repository.UserRepository;
import com.servinetcomputers.api.domain.user.persistence.JpaUserRepository;
import com.servinetcomputers.api.domain.user.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;

/**
 * The user's service implementation.
 */
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public UserResponse create(UserRequest request) {
        if (!request.passwordsMatch()) {
            throw new BadRequestException("Las contraseñas no coinciden");
        }

        final var entity = userMapper.toEntity(request);

        entity.setPassword(passwordEncoder.encode(request.password()));

        final var lastUser = jpaUserRepository.findFirstByRoleOrderByCreatedDateDesc(request.role());

        final var code = lastUser.map(user -> Integer.parseInt(user.getCode()
                .split(request.role()
                        .getRole()
                        .toLowerCase())
                [1])
        ).orElse(0);

        entity.setCode(request.role().getRole().toLowerCase() + (code + 1));

        return userMapper.toResponse(jpaUserRepository.save(entity));
    }

    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public List<UserResponse> getAll() {
        return userMapper.toResponses(jpaUserRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse get(int userId) {
        final var user = jpaUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + userId));

        if (user.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Usuario no encontrado: " + userId);
        }

        return userMapper.toResponse(user);
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public UserResponse update(int userId, UserRequest request) {
        final var user = jpaUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + userId));

        if (user.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Usuario no encontrado: " + userId);
        }

        user.setName(request.name() == null ? user.getName() : request.name());
        user.setLastName(request.lastName() == null ? user.getLastName() : request.lastName());

        return userMapper.toResponse(jpaUserRepository.save(user));
    }

    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public boolean delete(int userId) {
        final var userFound = jpaUserRepository.findById(userId);

        if (userFound.isEmpty()) {
            return false;
        }

        userFound.get().setEnabled(false);

        jpaUserRepository.save(userFound.get());

        return true;
    }
}
