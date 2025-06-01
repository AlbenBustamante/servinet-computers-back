package com.servinetcomputers.api.core.exception;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The app exceptions handler.
 */
@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    private final DateTimeService dateTimeService;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("{}: {}, Request Details: {}", ex.getBody().getTitle(), ex.getBody().getDetail(), request.getDescription(false), ex);

        final Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", timestamp());
        body.put("status", ex.getBody().getStatus());
        body.put("title", ex.getBody().getTitle());
        body.put("detail", ex.getBody().getDetail());
        body.put("ok", false);
        body.putAll(Objects.requireNonNull(ex.getBody().getProperties()));

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex, WebRequest request) {
        final var errorResponse = buildErrorResponse(ex, request);
        log.error("EXCEPTION: {}\nPATH: {}\nSTATUS CODE: {} - {}", errorResponse.message(), errorResponse.path(), errorResponse.error(), errorResponse.statusCode());

        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception ex, WebRequest request) {
        final var errorResponse = buildErrorResponse(ex, request);
        log.error("UNEXPECTED EXCEPTION: {}\nPATH: {}\nSTATUS CODE: {} - {}", errorResponse.message(), errorResponse.path(), errorResponse.error(), errorResponse.statusCode(), ex);

        return ResponseEntity.internalServerError().body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(Exception ex, WebRequest request) {
        final var internalError = HttpStatus.INTERNAL_SERVER_ERROR;

        final var message = ex.getMessage();
        final var path = request.getDescription(false).replace("uri=", "");
        final var error = ex instanceof AppException ? ((AppException) ex).getStatus().getReasonPhrase() : internalError.getReasonPhrase();
        final var statusCode = ex instanceof AppException ? ((AppException) ex).getStatus().value() : internalError.value();
        final var timestamp = timestamp();

        return new ErrorResponse(message, path, error, statusCode, timestamp);
    }

    /**
     * Gets the current timestamp and formats it.
     *
     * @return the formatted timestamp.
     */
    private String timestamp() {
        return dateTimeService.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
