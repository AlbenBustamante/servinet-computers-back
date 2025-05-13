package com.servinetcomputers.api.module.reports;

import com.servinetcomputers.api.module.reports.application.usecase.GetDashboardUseCase;
import com.servinetcomputers.api.module.reports.dto.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping(path = "/dashboard")
@RestController
public class DashboardController {
    private final GetDashboardUseCase getDashboardUseCase;

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard(@RequestParam(name = "date", required = false) LocalDate date) {
        return ResponseEntity.ok(getDashboardUseCase.call(date));
    }
}
