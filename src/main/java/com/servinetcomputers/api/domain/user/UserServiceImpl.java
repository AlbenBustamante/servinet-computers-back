package com.servinetcomputers.api.domain.user;

import com.servinetcomputers.api.domain.DataResponse;
import com.servinetcomputers.api.domain.PageResponse;
import com.servinetcomputers.api.domain.user.abs.IUserService;
import com.servinetcomputers.api.domain.user.abs.UserMapper;
import com.servinetcomputers.api.domain.user.abs.UserRepository;
import com.servinetcomputers.api.domain.user.model.dto.UserRequest;
import com.servinetcomputers.api.domain.user.model.dto.UserResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.BadRequestException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.security.util.SecurityConstants.USER_AUTHORITY;

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
        if (repository.existsByCode(request.code())) {
            throw new NotFoundException("El código ya se encuentra registrado");
        }

        if (!request.passwordsMatch()) {
            throw new BadRequestException("Las contraseñas no coinciden");
        }

        final var entity = mapper.toEntity(request);

        entity.setPassword(passwordEncoder.encode(request.password()));

        final var response = mapper.toResponse(repository.save(entity));

        return new PageResponse<>(201, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Override
    public PageResponse<UserResponse> get(int userId) {
        final var user = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + userId));

        if (user.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Usuario no encontrado: " + userId);
        }

        final var response = mapper.toResponse(user);

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = USER_AUTHORITY)
    @Override
    public PageResponse<UserResponse> update(int userId, UserRequest request) {
        final var user = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + userId));

        if (user.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Usuario no encontrado: " + userId);
        }

        user.setName(request.name() == null ? user.getName() : request.name());
        user.setLastName(request.lastName() == null ? user.getLastName() : request.lastName());

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

        userFound.get().setEnabled(false);

        repository.save(userFound.get());

        return true;
    }

}
