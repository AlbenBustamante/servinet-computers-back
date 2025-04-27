package com.servinetcomputers.api.core.page;

import java.util.List;

/**
 * The pagination response to show the data.
 */
public record PageResponse<T>(
        Pagination page,
        List<T> content
) {
    public record Pagination(
            int size,
            long totalElements,
            int totalPages,
            int currentPage
    ) {
    }
}
