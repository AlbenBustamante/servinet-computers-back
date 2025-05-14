package com.servinetcomputers.api.module.passwordtempcode.persistence.adapter;

import com.servinetcomputers.api.module.passwordtempcode.domain.adapter.PasswordTempCodePersistenceAdapter;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.CreatePasswordTempCodeDto;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.PasswordTempCodeDto;
import com.servinetcomputers.api.module.passwordtempcode.persistence.JpaPasswordTempCodeRepository;
import com.servinetcomputers.api.module.passwordtempcode.persistence.mapper.PasswordTempCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordTempCodePersistenceAdapterImpl implements PasswordTempCodePersistenceAdapter {
    private final JpaPasswordTempCodeRepository repository;
    private final PasswordTempCodeMapper mapper;

    @Override
    public PasswordTempCodeDto save(CreatePasswordTempCodeDto dto) {
        final var entity = mapper.toEntity(dto);
        final var newTempCode = repository.save(entity);

        return mapper.toDto(newTempCode);
    }

    @Override
    public boolean isUsed(String code) {
        return repository.existsByCodeAndEnabledTrueAndUsedByIsNull(code);
    }
}
