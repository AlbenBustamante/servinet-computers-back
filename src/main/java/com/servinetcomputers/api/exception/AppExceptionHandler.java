package com.servinetcomputers.api.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The app exceptions handler.
 */
@RequiredArgsConstructor
@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    private final ZoneId zoneId;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", timestamp());
        body.put("status", ex.getBody().getStatus());
        body.put("title", ex.getBody().getTitle());
        body.put("detail", ex.getBody().getDetail());
        body.put("ok", false);
        body.putAll(Objects.requireNonNull(ex.getBody().getProperties()));

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = {AlreadyExistsException.class, BadRequestException.class, NotFoundException.class, AppException.class})
    public ProblemDetail handleAppException(AppException ex) {
        return createProblemDetail(ex);
    }

    /**
     * Creates a generic {@link ProblemDetail} for the father exception.
     *
     * @param ex the {@link AppException}.
     * @return the generic {@link ProblemDetail} generated.
     */
    private ProblemDetail createProblemDetail(AppException ex) {
        final var problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getLocalizedMessage());

        problemDetail.setProperty("timestamp", timestamp());
        problemDetail.setProperty("ok", false);

        return problemDetail;
    }

    /**
     * Gets the current timestamp and formats it.
     *
     * @return the formatted timestamp.
     */
    private String timestamp() {
        return LocalDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

}
