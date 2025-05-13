package com.servinetcomputers.api.module.changelog.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogDto;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;

/**
 * Creates a new change log.
 * <p>Receives a {@link CreateChangeLogDto}.</p>
 * <p>Returns a {@link ChangeLogDto}.</p>
 */
public interface CreateChangeLogUseCase extends UseCase<ChangeLogDto, CreateChangeLogDto> {
}
