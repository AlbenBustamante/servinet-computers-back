package com.servinetcomputers.api.module.user.infrastructure.out;

import com.servinetcomputers.api.core.common.PersistenceAdapter;
import com.servinetcomputers.api.core.util.enums.Role;
import com.servinetcomputers.api.module.user.application.port.out.UserReadPort;
import com.servinetcomputers.api.module.user.application.port.out.UserWritePort;
import com.servinetcomputers.api.module.user.domain.User;
import com.servinetcomputers.api.module.user.exception.EmailNotFoundByCodeException;
import com.servinetcomputers.api.module.user.exception.UserNotFoundException;
import com.servinetcomputers.api.module.user.infrastructure.out.persistence.UserJpaRepository;
import com.servinetcomputers.api.module.user.infrastructure.out.persistence.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserReadPort, UserWritePort {
    private final UserJpaRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmailAndEnabledTrue(email);
    }

    @Override
    public boolean existsByCode(String code) {
        return repository.existsByCodeAndEnabledTrue(code);
    }

    @Override
    public String getEmailByCode(String code) {
        return repository.findEmailByCodeAndEnabledTrue(code).orElseThrow(() -> new EmailNotFoundByCodeException(code));
    }

    @Override
    public Optional<User> getLastByRole(Role role) {
        final var user = repository.findFirstByRoleOrderByCreatedDateDesc(role);
        return user.map(mapper::toDomain);
    }

    @Override
    public List<User> getAll() {
        return mapper.toDomains(repository.findAll());
    }

    @Override
    public User get(int userId) {
        final var user = repository.findByIdAndEnabledTrue(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return mapper.toDomain(user);
    }

    @Override
    public User save(User user) {
        final var entity = mapper.toEntity(user);
        final var newUser = repository.save(entity);

        return mapper.toDomain(newUser);
    }

    @Override
    public void savePassword(String userCode, String password) {
        final var user = repository.findByCodeAndEnabledTrue(userCode).orElseThrow(() -> new UserNotFoundException(userCode));
        user.setPassword(password);
    }
}
