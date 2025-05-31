package com.servinetcomputers.api.core.exporter;

import com.servinetcomputers.api.module.user.domain.dto.JourneyDetailDto;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

@Component
public class JourneyExcelExporter {
    private void writeHeader(XSSFWorkbook workbook, XSSFSheet sheet) {
        final var row = sheet.createRow(0);
        final var style = workbook.createCellStyle();
        final var font = workbook.createFont();

        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Fecha", style, sheet);
        createCell(row, 1, "Hora Entrada", style, sheet);
        createCell(row, 2, "Almuerzo Entrada", style, sheet);
        createCell(row, 3, "Almuerzo Salida", style, sheet);
        createCell(row, 4, "Hora Salida", style, sheet);
        createCell(row, 5, "Por descontar", style, sheet);
        createCell(row, 6, "Horas trabajadas", style, sheet);
    }

    private void writeBody(JourneyDetailDto detail, XSSFWorkbook workbook, XSSFSheet sheet) {
        final var font = workbook.createFont();
        font.setFontHeight(14);

        final var dateStyle = createStyle(workbook, font, "dd/MM/yyyy");
        final var timeStyle = createStyle(workbook, font, "HH:mm:ss");
        final var currencyStyle = createStyle(workbook, font, "$#,##0");

        var rowCount = 1;

        for (final var journey : detail.journeys()) {
            final var cashRegisterDetail = journey.cashRegisterDetail();
            final var createdDate = cashRegisterDetail.getCreatedDate();
            final var initialWorking = cashRegisterDetail.getInitialWorking();
            final var initialBreak = cashRegisterDetail.getInitialBreak();
            final var finalBreak = cashRegisterDetail.getFinalBreak();
            final var finalWorking = cashRegisterDetail.getFinalWorking();

            final var row = sheet.createRow(rowCount++);
            var columnCount = 0;

            createCell(row, columnCount++, createdDate, dateStyle, sheet);
            createCell(row, columnCount++, initialWorking, timeStyle, sheet);
            createCell(row, columnCount++, initialBreak, timeStyle, sheet);
            createCell(row, columnCount++, finalBreak, timeStyle, sheet);
            createCell(row, columnCount++, finalWorking, timeStyle, sheet);
            createCell(row, columnCount++, journey.totalOfDiscounts(), currencyStyle, sheet);
            createCell(row, columnCount, parse(journey.totalOfHours()), timeStyle, sheet);
        }

        final var totalRow = sheet.createRow(++rowCount);

        final var boldStyle = workbook.createCellStyle();
        final var boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldFont.setFontHeight(16);
        boldStyle.setFont(boldFont);

        createCell(totalRow, 0, "TOTAL", boldStyle, sheet);
        createCell(totalRow, 5, detail.totalOfDiscounts(), currencyStyle, sheet);
        createCell(totalRow, 6, detail.totalOfHours(), boldStyle, sheet);

        final var columns = 7;

        for (var i = 0; i < columns; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void export(JourneyDetailDto detail, YearMonth month, HttpServletResponse response) throws IOException {
        try (final var workbook = new XSSFWorkbook()) {
            final var formattedMonth = String.format("%s %d", month.getMonth().name(), month.getYear());
            final var sheet = workbook.createSheet(formattedMonth);

            writeHeader(workbook, sheet);
            writeBody(detail, workbook, sheet);

            final var outputStream = response.getOutputStream();
            workbook.write(outputStream);
        }
    }

    private XSSFCellStyle createStyle(XSSFWorkbook workbook, XSSFFont font, String format) {
        final var style = workbook.createCellStyle();
        style.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(format));
        style.setFont(font);

        return style;
    }

    private LocalDateTime parse(String value) {
        final var time = LocalTime.parse(value);
        final var date = LocalDate.now();
        return LocalDateTime.of(date, time);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style, XSSFSheet sheet) {
        final var cell = row.createCell(columnCount);

        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }

        if (value instanceof String) {
            cell.setCellValue((String) value);
        }

        if (value instanceof LocalDateTime) {
            cell.setCellValue((LocalDateTime) value);
        }

        cell.setCellStyle(style);
    }
}
