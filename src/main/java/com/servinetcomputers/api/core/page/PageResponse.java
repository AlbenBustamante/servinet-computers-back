package com.servinetcomputers.api.core.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The pagination response to show the data.
 */
@AllArgsConstructor
@Getter
@Setter
public class PageResponse<T> {
    private Pagination page;
    private List<T> content;

    public record Pagination(
            int size,
            long totalElements,
            int totalPages,
            int currentPage
    ) {
    }
}
