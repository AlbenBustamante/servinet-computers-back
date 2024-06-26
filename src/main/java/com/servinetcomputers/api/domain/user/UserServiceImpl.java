package com.servinetcomputers.api.domain.user;

import com.servinetcomputers.api.domain.user.abs.IUserService;
import com.servinetcomputers.api.domain.user.abs.UserMapper;
import com.servinetcomputers.api.domain.user.abs.UserRepository;
import com.servinetcomputers.api.domain.user.dto.UserRequest;
import com.servinetcomputers.api.domain.user.dto.UserResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.BadRequestException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.security.util.SecurityConstants.ADMIN_AUTHORITY;

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
    public UserResponse create(UserRequest request) {
        if (!request.passwordsMatch()) {
            throw new BadRequestException("Las contraseñas no coinciden");
        }

        final var entity = mapper.toEntity(request);

        entity.setPassword(passwordEncoder.encode(request.password()));

        final var lastUser = repository.findFirstByRoleOrderByCreatedDateDesc(request.role());

        final var code = lastUser.map(user -> Integer.parseInt(user.getCode()
                .split(request.role()
                        .getRole()
                        .toLowerCase())
                [1])
        ).orElse(0);

        entity.setCode(request.role().getRole().toLowerCase() + (code + 1));

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<UserResponse> getAll() {
        return mapper.toResponses(repository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse get(int userId) {
        final var user = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + userId));

        if (user.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Usuario no encontrado: " + userId);
        }

        return mapper.toResponse(user);
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public UserResponse update(int userId, UserRequest request) {
        final var user = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + userId));

        if (user.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Usuario no encontrado: " + userId);
        }

        user.setName(request.name() == null ? user.getName() : request.name());
        user.setLastName(request.lastName() == null ? user.getLastName() : request.lastName());

        return mapper.toResponse(repository.save(user));
    }

    @Secured(value = ADMIN_AUTHORITY)
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
