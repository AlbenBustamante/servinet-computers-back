package com.servinetcomputers.api.module.changelog.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogResponse;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;

/**
 * Creates a new change log.
 * <p>Receives a {@link CreateChangeLogDto}.</p>
 * <p>Returns a {@link ChangeLogResponse}.</p>
 */
public interface CreateChangeLogUseCase extends UseCase<ChangeLogResponse, CreateChangeLogDto> {
}
