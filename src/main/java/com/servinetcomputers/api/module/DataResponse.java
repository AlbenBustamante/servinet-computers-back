package com.servinetcomputers.api.module;

import java.util.List;

/**
 * Contains info about the pagination and results.
 */
public record DataResponse<T extends ModelResponse>(long totalElements, int currentPage, int totalPages,
                                                    List<T> results) {
}
