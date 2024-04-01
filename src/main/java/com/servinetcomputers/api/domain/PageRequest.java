package com.servinetcomputers.api.domain;

import org.springframework.data.domain.Sort;

/**
 * Request basic info to set the pagination.
 */
public record PageRequest(int size, int page, Sort.Direction direction, String property) {

    public org.springframework.data.domain.PageRequest toPageable() {
        return org.springframework.data.domain.PageRequest.of(page, size, direction, property);
    }

}
