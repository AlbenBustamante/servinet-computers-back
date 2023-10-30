package com.servinetcomputers.api.dto.response;

import java.util.List;

/**
 * Contains info about the pagination and results.
 */
public record DataResponse<T extends ModelResponse>(int totalElements, int currentPage, List<T> results) {
}
