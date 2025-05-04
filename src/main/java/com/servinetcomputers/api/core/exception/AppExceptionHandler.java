package com.servinetcomputers.api.core.exception;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
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
        log.error("{}: {}", ex.getBody().getTitle(), ex.getBody().getDetail());

        final Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", timestamp());
        body.put("status", ex.getBody().getStatus());
        body.put("title", ex.getBody().getTitle());
        body.put("detail", ex.getBody().getDetail());
        body.put("ok", false);
        body.putAll(Objects.requireNonNull(ex.getBody().getProperties()));

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = {
            AuthenticationException.class,
            RequiredTempCodeException.class,
            InvalidTempCodeException.class,
            AlreadyExistsException.class,
            BadRequestException.class,
            NotFoundException.class,
            AppException.class
    })
    public ProblemDetail handleAppException(AppException ex) {
        log.error(ex.getMessage());
        return createProblemDetail(ex);
    }

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleRuntime(RuntimeException ex) {
        log.error(ex.getMessage());
        final var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setProperty("timestamp", timestamp());

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handle(Exception ex) {
        log.error(ex.getMessage());
        final var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setProperty("timestamp", timestamp());

        return problemDetail;
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
        return dateTimeService.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
