package com.servinetcomputers.api.dto.response;

import java.util.List;

/**
 * The Admin Dashboard Reports dto model for responses.
 */
public record DashboardResponse(String total, List<CampusDashboardResponse> campuses) {

    public record CampusDashboardResponse(int numeral, String total) {
    }
}
