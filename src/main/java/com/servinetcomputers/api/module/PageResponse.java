package com.servinetcomputers.api.module;

/**
 * The pagination response to show the data.
 */
public record PageResponse<T extends ModelResponse>(int statusCode, boolean ok, DataResponse<T> data) {
}
