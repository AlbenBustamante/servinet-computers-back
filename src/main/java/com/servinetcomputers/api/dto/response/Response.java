package com.servinetcomputers.api.dto.response;

import java.util.List;

/**
 * Generic response to show the data.
 */
public record Response<T>(List<T> data, int statusCode) {
}
