package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.core.exporter.JourneyExcelExporter;
import com.servinetcomputers.api.module.user.application.usecase.ExportJourneysToExcelUseCase;
import com.servinetcomputers.api.module.user.application.usecase.GetJourneysUseCase;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.YearMonth;

@RequiredArgsConstructor
@Service
public class ExportJourneysToExcelService implements ExportJourneysToExcelUseCase {
    private final JourneyExcelExporter exporter;
    private final GetJourneysUseCase getJourneysUseCase;

    @Override
    public void export(Integer userId, YearMonth month, HttpServletResponse response) throws IOException {
        final var journeys = getJourneysUseCase.call(userId, month);
        exporter.export(journeys, month, response);
    }
}
