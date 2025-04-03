package com.polarbookshop.catalog.exception;

import com.polarbookshop.catalog.model.consumer.rest.EmptyDataResponse;
import com.polarbookshop.catalog.model.consumer.rest.Notification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.polarbookshop.catalog.shared.logging.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.invoke.MethodHandles;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@ControllerAdvice
public class BookExceptionController {

    private static final Logger LOG = Logger.get(MethodHandles.lookup().lookupClass());
    private static final String X_CONTENT_TYPE_OPTIONS = "X-Content-Type-Options";
    private static final String X_FRAME_OPTIONS = "X-Frame-Options";
    private static final String STRICT_TRANSPORT_SECURITY = "Strict-Transport-Security";

    @ExceptionHandler(BookNotFoundException.class)
    public final ResponseEntity<EmptyDataResponse> handleBookNotFoundExceptions(
            BookNotFoundException ex
    ) {
        LOG.error(event -> event.message("BookNotFoundException : CatalogBusinessException Generic Exception.")
                .with("ExceptionMessage", ex.getMessage())
                .with("ExceptionLocalizedMessage", ex.getLocalizedMessage())
                .with("PolarExceptionHandler", "CatalogBusinessException")
                .tag("CatalogBusinessException"));

        EmptyDataResponse error = new EmptyDataResponse();
        List<Notification> notifications = new ArrayList<>();
        Notification notification = new Notification();
        notification.setCode("E001");
        notification.setSeverity("ERROR");
        notification.setDescription(ex.getMessage());
        notification.setMessage(ex.getMessage());
        notification.setUuid(UUID.randomUUID().toString());
        notification.setNotificationDt(OffsetDateTime.now());

        notifications.add(notification);
        error.setNotifications(notifications);
        return new ResponseEntity<>(error, getResponseHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public final ResponseEntity<EmptyDataResponse> handleBookAlreadyExistsExceptions(
            BookAlreadyExistsException ex
    ) {
        LOG.error(event -> event.message("BookAlreadyExistsException : CatalogBusinessException Generic Exception.")
                .with("ExceptionMessage", ex.getMessage())
                .with("ExceptionLocalizedMessage", ex.getLocalizedMessage())
                .with("PolarExceptionHandler", "CatalogBusinessException")
                .tag("CatalogBusinessException"));

        EmptyDataResponse error = new EmptyDataResponse();
        List<Notification> notifications = new ArrayList<>();
        Notification notification = new Notification();
        notification.setCode("E001");
        notification.setSeverity("ERROR");
        notification.setDescription(ex.getMessage());
        notification.setMessage(ex.getMessage());
        notification.setUuid(UUID.randomUUID().toString());
        notification.setNotificationDt(OffsetDateTime.now());

        notifications.add(notification);
        error.setNotifications(notifications);
        return new ResponseEntity<>(error, getResponseHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<EmptyDataResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        LOG.error(event -> event.message(
                        "CatalogExceptionMessage : CatalogBusinessException > MethodArgumentNotValidException.")
                .with("ExceptionMessage", ex.getMessage())
                .with("ExceptionLocalizedMessage", ex.getLocalizedMessage())
                .with("CatalogExceptionHandler", "MethodArgumentNotValidException")
                .tag("CatalogBusinessException"));
        List<Notification> notifications = new ArrayList<>();
        ex.getFieldErrors()
                .stream()
                .filter(Objects::nonNull)
                .filter(error -> Objects.nonNull(error.getDefaultMessage()))
                .forEach(error -> {
                    Notification notification = new Notification();
                    notification.setSeverity("ERROR");
                    notification.setDescription(error.getDefaultMessage());
                    notification.setMessage(error.getDefaultMessage());
                    notification.setFieldName(error.getField());
                    notification.setUuid(UUID.randomUUID().toString());
                    notification.setNotificationDt(OffsetDateTime.now());

                    notifications.add(notification);
                });
        EmptyDataResponse error = new EmptyDataResponse();
        error.setNotifications(notifications);
        return new ResponseEntity<>(error, getResponseHeaders(), HttpStatus.BAD_REQUEST);
    }

    private HttpHeaders getResponseHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(STRICT_TRANSPORT_SECURITY, "max-age=3153600; includeSubDomains");
        headers.add(X_FRAME_OPTIONS, "DENY");
        headers.add(X_CONTENT_TYPE_OPTIONS, "nosniff");

        return headers;
    }
}
