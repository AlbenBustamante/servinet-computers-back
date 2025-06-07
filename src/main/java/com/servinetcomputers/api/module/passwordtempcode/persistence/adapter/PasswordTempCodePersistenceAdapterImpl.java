package com.servinetcomputers.api.module.passwordtempcode.persistence.adapter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.passwordtempcode.domain.adapter.PasswordTempCodePersistenceAdapter;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.CreatePasswordTempCodeDto;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.PasswordTempCodeDto;
import com.servinetcomputers.api.module.passwordtempcode.persistence.JpaPasswordTempCodeRepository;
import com.servinetcomputers.api.module.passwordtempcode.persistence.mapper.PasswordTempCodeMapper;
import com.servinetcomputers.api.module.user.infrastructure.out.persistence.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PasswordTempCodePersistenceAdapterImpl implements PasswordTempCodePersistenceAdapter {
    private final JpaPasswordTempCodeRepository repository;
    private final UserJpaRepository userRepository;
    private final PasswordTempCodeMapper mapper;

    @Override
    public PasswordTempCodeDto save(CreatePasswordTempCodeDto dto) {
        final var entity = mapper.toEntity(dto);
        final var newTempCode = repository.save(entity);

        return mapper.toDto(newTempCode);
    }

    @Override
    public boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }

    @Override
    public List<PasswordTempCodeDto> getAllByUserCode(String userCode) {
        final var tempCodes = repository.findAllByUserCode(userCode);
        return mapper.toDto(tempCodes);
    }

    @Override
    public void setUsedBy(Integer passwordTempCodeId, String userCode) {
        final var passwordTempCode = repository.findById(passwordTempCodeId)
                .orElseThrow(() -> new NotFoundException("No se encontró el código: " + passwordTempCodeId));

        final var user = userRepository.findByCodeAndEnabledTrue(userCode)
                .orElseThrow(() -> new NotFoundException("No se encontró al usuario: " + userCode));

        passwordTempCode.setUsedBy(user);
    }
}
