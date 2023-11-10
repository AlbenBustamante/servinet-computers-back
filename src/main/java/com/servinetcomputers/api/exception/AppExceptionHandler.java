package com.servinetcomputers.api.exception;

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
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The app exceptions handler.
 */
@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(value = {CampusUnavailableException.class, CampusNotFoundException.class})
    public ProblemDetail handleCampusIdException(CampusIdException ex) {
        final var problemDetail = createProblemDetail(ex);
        problemDetail.setProperty("campusId", ex.getCampusId());

        return problemDetail;
    }

    @ExceptionHandler(value = {UserUnavailableException.class, UserNotFoundException.class})
    public ProblemDetail handleUserIdException(UserIdException ex) {
        final var problemDetail = createProblemDetail(ex);
        problemDetail.setProperty("userId", ex.getUserId());

        return problemDetail;
    }

    @ExceptionHandler(value = {PlatformUnavailableException.class, PlatformNotFoundException.class})
    public ProblemDetail handlePlatformIdException(PlatformIdException ex) {
        final var problemDetail = createProblemDetail(ex);
        problemDetail.setProperty("platformId", ex.getPlatformId());

        return problemDetail;
    }

    @ExceptionHandler(value = PlatformNameNotFoundException.class)
    public ProblemDetail handlePlatformNameNotFoundException(PlatformNameNotFoundException ex) {
        final var problemDetail = createProblemDetail(ex);
        problemDetail.setProperty("platformName", ex.getPlatformName());

        return problemDetail;
    }

    @ExceptionHandler(value = {TransferUnavailableException.class, TransferNotFoundException.class})
    public ProblemDetail handleTransferIdException(TransferIdException ex) {
        final var problemDetail = createProblemDetail(ex);
        problemDetail.setProperty("transferId", ex.getTransferId());

        return problemDetail;
    }

    @ExceptionHandler(value = {CampusAlreadyExistsException.class, UserAlreadyExistsException.class, PlatformAlreadyExistsException.class})
    public ProblemDetail handleAlreadyExists(AlreadyExistsException ex) {
        final var problemDetail = createProblemDetail(ex);
        problemDetail.setProperty("property", ex.getProperty());

        return problemDetail;
    }

    @ExceptionHandler(value = {PasswordsDoNotMatchException.class})
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

        problemDetail.setTitle(ex.getTitle());
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
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss"));
    }

}
