package com.servinetcomputers.api.domain;

/**
 * The pagination response to show the data.
 */
public record PageResponse<T extends ModelResponse>(int statusCode, boolean ok, DataResponse<T> data) {
}
