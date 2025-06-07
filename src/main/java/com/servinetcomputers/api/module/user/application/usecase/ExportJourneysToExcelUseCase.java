package com.servinetcomputers.api.module.user.application.usecase;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.YearMonth;

public interface ExportJourneysToExcelUseCase {
    void export(Integer userId, YearMonth month, HttpServletResponse response) throws IOException;
}
