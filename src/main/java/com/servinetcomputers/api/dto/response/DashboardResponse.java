package com.servinetcomputers.api.dto.response;

import java.util.List;

/**
 * The Admin Dashboard Reports dto model for responses.
 */
public record DashboardResponse(String total,
                                List<PlatformDetailDashboardResponse> temporaryPlatforms,
                                List<PlatformDetailDashboardResponse> platforms) {

    public record CampusDashboardResponse(int numeral, String total) {
    }

    public record PlatformDetailDashboardResponse(int platformId, String platformName, int transfersAmount,
                                                  String total) {
    }
}
