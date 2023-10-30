package com.servinetcomputers.api.dto.request;

import org.springframework.data.domain.Sort;

/**
 * Request basic info to set the pagination.
 */
public record PageRequest(int size, int page, Sort.Direction direction, String property) {
}
