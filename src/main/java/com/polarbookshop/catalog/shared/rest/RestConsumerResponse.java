package com.polarbookshop.catalog.shared.rest;

import com.fasterxml.jackson.annotation.*;
import lombok.ToString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serial;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@ToString
public class RestConsumerResponse<T> implements IConsumerResponse, ISiemTransactionable {
    @Serial
    private static final long serialVersionUID = -7567835039103237262L;

    /**
     * Create a new {@link RestConsumerResponse} which has only a {@code notifications}
     * body and a {@code null data}.
     *
     * @param notifications The {@link Notification}'s to add to the response*
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static RestConsumerResponse<Void> error(final Notification... notifications) {
        return new RestConsumerResponse<>(null, null, transformNotification(Arrays.asList(notifications), null), null);
    }

    public static RestConsumerResponse<Void> error(final List<Notification> notifications) {
        return new RestConsumerResponse<>(null, null, transformNotification(notifications, null), null);
    }

    /**
     * Create a new {@link RestConsumerResponse} which has only a {@code notifications}
     * body and a {@code null data}.
     *
     * @param httpStatus The {@link HttpStatus} to add to the response
     * @param notifications The {@link Notification}'s to add to the response
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static RestConsumerResponse<Void> error(final HttpStatus httpStatus, final Notification... notifications) {
        return new RestConsumerResponse<>(null, null, transformNotification(Arrays.asList(notifications), httpStatus), null, httpStatus);
    }

    public static RestConsumerResponse<Void> error(final HttpStatus httpStatus, final List<Notification> notifications) {
        return new RestConsumerResponse<>(null, null, transformNotification(notifications, httpStatus), null, httpStatus);
    }

    /**
     * Create a new {@link RestConsumerResponse} with only a {@code data} element equal to the
     * given value.
     *
     * @param value The value of the response {@code data}
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value) {
        return new RestConsumerResponse<>(value, null, Collections.emptyList(), null);
    }

    /**
     * Create a new {@link RestConsumerResponse} with only a {@code data} element equal to the
     * given value.
     *
     * @param value The value of the response {@code data}
     * @param httpStatus The {@link HttpStatus} to add to the response
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final HttpStatus httpStatus) {
        return new RestConsumerResponse<>(value, null, Collections.emptyList(), null, httpStatus);
    }

    /**
     * Create a new {@link RestConsumerResponse} with only a {@code data} element equal to the
     * given value.
     *
     * @param value     The value of the response {@code data}
     * @param paging    The value of the response {@code paging}
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final Paging paging) {
        return new RestConsumerResponse<>(value, paging, Collections.emptyList(), null);
    }

    /**
     * Create a new {@link RestConsumerResponse} with only a {@code data} element equal to the
     * given value.
     *
     * @param value     The value of the response {@code data}
     * @param paging    The value of the response {@code paging}
     * @param httpStatus The {@link HttpStatus} to add to the response
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final Paging paging, final HttpStatus httpStatus) {
        return new RestConsumerResponse<>(value, paging, Collections.emptyList(), null, httpStatus);
    }

    /**
     * Create a new {@link RestConsumerResponse} with only a {@code data} element equal to the
     * given value.
     *
     * @param value The value of the response {@code data}
     * @param httpHeaders The {@link HttpHeaders}'s to add to the response
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final HttpHeaders httpHeaders) {
        return new RestConsumerResponse<>(value, null, Collections.emptyList(), httpHeaders);
    }

    /**
     * Create a new {@link RestConsumerResponse} with only a {@code data} element equal to the
     * given value.
     *
     * @param value The value of the response {@code data}
     * @param httpHeaders The {@link HttpHeaders}'s to add to the response
     * @param httpStatus The {@link HttpStatus} to add to the response
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final HttpHeaders httpHeaders, final HttpStatus httpStatus) {
        return new RestConsumerResponse<>(value, null, Collections.emptyList(), httpHeaders, httpStatus);
    }

    /**
     * Create a new {@link RestConsumerResponse} with only a {@code data} element equal to the
     * given value.
     *
     * @param value The value of the response {@code data}
     * @param paging    The value of the response {@code paging}
     * @param httpHeaders The {@link HttpHeaders}'s to add to the response
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final Paging paging, final HttpHeaders httpHeaders) {
        return new RestConsumerResponse<>(value, paging, Collections.emptyList(), httpHeaders);
    }

    /**
     * Create a new {@link RestConsumerResponse} with only a {@code data} element equal to the
     * given value.
     *
     * @param value The value of the response {@code data}
     * @param paging    The value of the response {@code paging}
     * @param httpHeaders The {@link HttpHeaders}'s to add to the response
     * @param httpStatus The {@link HttpStatus} to add to the response
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final Paging paging, final HttpHeaders httpHeaders, final HttpStatus httpStatus) {
        return new RestConsumerResponse<>(value, paging, Collections.emptyList(), httpHeaders, httpStatus);
    }

    /**
     * Create a new {@link RestConsumerResponse} with a value and List of {@link Notification} notifications.
     *
     * @param value             The value of the response {@code data}
     * @param notifications     The value of the response {@code notifications}
     *
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final List<Notification> notifications) {
        return new RestConsumerResponse<>(value, null, transformNotification(notifications, null), null);
    }

    /**
     * Create a new {@link RestConsumerResponse} with a value and List of {@link Notification} notifications.
     *
     * @param value             The value of the response {@code data}
     * @param notifications     The value of the response {@code notifications}
     * @param httpStatus The {@link HttpStatus} to add to the response
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final List<Notification> notifications, final HttpStatus httpStatus) {
        return new RestConsumerResponse<>(value, null, transformNotification(notifications, httpStatus), null, httpStatus);
    }

    /**
     * Create a new {@link RestConsumerResponse} with a value, a paging and List of {@link Notification} notifications.
     *
     * @param value             The value of the response {@code data}
     * @param paging            The value of the response {@code paging}
     * @param notifications     The value of the response {@code notifications}
     *
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final Paging paging, final List<Notification> notifications) {
        return new RestConsumerResponse<>(value, paging, transformNotification(notifications, null), null);
    }


    /**
     * Create a new {@link RestConsumerResponse} with a value, a paging and List of {@link Notification} notifications.
     *
     * @param value             The value of the response {@code data}
     * @param paging            The value of the response {@code paging}
     * @param notifications     The value of the response {@code notifications}
     * @param httpStatus The {@link HttpStatus} to add to the response
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final Paging paging, final List<Notification> notifications, final HttpStatus httpStatus) {
        return new RestConsumerResponse<>(value, paging, transformNotification(notifications, httpStatus), null, httpStatus);
    }

    /**
     * Create a new {@link RestConsumerResponse} with a value and List of {@link Notification} notifications.
     *
     * @param value             The value of the response {@code data}
     * @param notifications     The value of the response {@code notifications}
     * @param httpHeaders       The value of the response's  {@link HttpHeaders}
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final List<Notification> notifications, final HttpHeaders httpHeaders) {
        return new RestConsumerResponse<>(value, null, transformNotification(notifications, null), httpHeaders);
    }

    /**
     * Create a new {@link RestConsumerResponse} with a value and List of {@link Notification} notifications.
     *
     * @param value             The value of the response {@code data}
     * @param notifications     The value of the response {@code notifications}
     * @param httpHeaders       The value of the response's  {@link HttpHeaders}
     * @param httpStatus The {@link HttpStatus} to add to the response
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final List<Notification> notifications, final HttpHeaders httpHeaders, final HttpStatus httpStatus) {
        return new RestConsumerResponse<>(value, null, transformNotification(notifications, httpStatus), httpHeaders, httpStatus);
    }

    /**
     * Create a new {@link RestConsumerResponse} with a value, a paging and List of {@link Notification} notifications.
     *
     * @param value             The value of the response {@code data}
     * @param paging            The value of the response {@code paging}
     * @param notifications     The value of the response {@code notifications}
     * @param httpHeaders       The value of the response's  {@link HttpHeaders}
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final Paging paging, final List<Notification> notifications, final HttpHeaders httpHeaders) {
        return new RestConsumerResponse<>(value, paging, transformNotification(notifications, null), httpHeaders);
    }

    /**
     * Create a new {@link RestConsumerResponse} with a value, a paging and List of {@link Notification} notifications.
     *
     * @param value             The value of the response {@code data}
     * @param paging            The value of the response {@code paging}
     * @param notifications     The value of the response {@code notifications}
     * @param httpHeaders       The value of the response's  {@link HttpHeaders}
     * @param httpStatus The {@link HttpStatus} to add to the response
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> of(final T value, final Paging paging, final List<Notification> notifications,
                                                 final HttpHeaders httpHeaders, final HttpStatus httpStatus) {
        return new RestConsumerResponse<>(value, paging, transformNotification(notifications, httpStatus), httpHeaders, httpStatus);
    }

    /**
     * Create a new {@link RestConsumerResponse} with no {@code data} and blank {@code notifications}. This
     * would normally be used to represent a {@code NO CONTENT} response, or stubbing a typed response with
     * value null.
     *
     * @return A new {@link RestConsumerResponse}
     */
    public static <T> RestConsumerResponse<T> emptyResponse() {
        return new RestConsumerResponse<>(null, Collections.emptyList(), null);
    }

    protected final T            data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected final Paging       pagingCursor;

    @JsonFilter("ignoreHeaders")
    protected final HttpHeaders httpHeaders;
    protected final List<Notification> notifications;
    protected HttpStatus httpStatus = HttpStatus.OK;

    /**
     * Get the value of this response.
     *
     * @return The response value.
     */
    @JsonProperty("data")
    public Optional<T> get() {
        return Optional.ofNullable(this.data);
    }

    /**
     * Query method to determine if this response has a value.
     *
     * @return {@code true} if {@link #data} is not {@code null}
     */
    @JsonIgnore
    public boolean isPresent() {
        return this.data != null;
    }

    /**
     * Get the {@link #pagingCursor} instances in this response.
     * if any.
     *
     * @return the paging {@link #pagingCursor} instances, possibly empty
     */
    @JsonProperty("paging")
    public Paging getPagingCursor() {
        return this.pagingCursor;
    }

    /**
     * Get the {@link #notifications} instances in this response.
     * if any.
     *
     * @return the notification {@link #notifications} instances, possibly empty
     */
    @JsonProperty("notifications")
    public List<Notification> getNotifications() {
        return this.notifications;
    }

    /**
     * Get the {@link #httpHeaders} instances in this response.
     * if any.
     *
     * @return the httpHeaders {@link #httpHeaders} instances, possibly empty
     */
    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    /**
     * Get the {@link #httpHeaders} instances in this response.
     * if any.
     *
     * @return the httpHeaders {@link #httpHeaders} instances, possibly empty
     */
    @JsonProperty("httpHeaders")
    @JsonIgnore
    public HttpHeaders getHttpHeaders() {
        return this.httpHeaders;
    }

    /**
     * Query method to determine if {@code this} has any {@link Notification}'s
     * attached to it.
     *
     * @return {@code true} if notifications is not empty.
     */
    @JsonIgnore
    public boolean hasNotifications() {
        return this.notifications != null && !this.notifications.isEmpty();
    }

    /**
     * If a {@link #data} is present, invoke the specified {@link Consumer} with
     * it, otherwise do nothing.
     *
     * @param consumer The {@link Consumer} to be executed if a value is present
     */
    public void ifPresent(final Consumer<T> consumer) {
        if (this.data != null) {
            consumer.accept(this.data);
        }
    }

    /**
     * Construct the correct {@link ResponseEntity} from the state of {@code this}
     * and passes {@link HttpStatus}.
     * <p>
     * If there are no {@link Notification} instances then the response will contain
     * a body of {@code this} and an {@link HttpStatus} of {@link HttpStatus#OK}. If
     * the body is {@code null} then a {@link HttpStatus#NO_CONTENT} is set.
     *
     * @param httpStatus the {@link HttpStatus} to be set to the {@link ResponseEntity}
     *
     * @return A new {@link ResponseEntity} with a {@link HttpStatus} and body
     */
    public ResponseEntity<RestConsumerResponse<T>> entity(final HttpStatus httpStatus) {
        if (this.notifications == null || this.notifications.isEmpty()) {
            return this.data == null ? ResponseEntity.status(HttpStatus.NO_CONTENT).headers(httpHeaders).build()
                    : ResponseEntity.status(httpStatus).headers(httpHeaders).body(this);
        }

        return ResponseEntity.status(httpStatus).headers(httpHeaders).body(this);
    }

    /**
     * Construct the correct {@link ResponseEntity} from the state of {@code this}
     * and passes {@link HttpStatus}.
     * <p>
     * If there are no {@link Notification} instances then the response will contain
     * a body of {@code this} and an {@link HttpStatus} of {@link HttpStatus#OK}. If
     * the body is {@code null} then a {@link HttpStatus#NO_CONTENT} is set.
     *
     *
     *
     * @return A new {@link ResponseEntity} with a {@link HttpStatus} and body
     */
    public ResponseEntity<RestConsumerResponse<T>> entity() {
        return ResponseEntity.status(httpStatus).headers(httpHeaders).body(this);
    }

    /**
     * Basic constructor for the class.
     *
     * @param data The response body
     * @param notifications The {@link Notification}'s to add to the response
     * @param httpHeaders The {@link HttpHeaders}'s to add to the response
     */
    public RestConsumerResponse(@JsonProperty("data")final T data,
                                @JsonProperty("notifications")final List<Notification> notifications,
                                @JsonProperty("httpHeaders") final HttpHeaders httpHeaders) {
        this.data = data;
        this.pagingCursor = null;
        this.notifications = notifications;
        this.httpHeaders = httpHeaders;
    }

    /**
     * Basic constructor for the class with httpStatus.
     *
     * @param data The response body
     * @param notifications The {@link Notification}'s to add to the response
     * @param httpHeaders The {@link HttpHeaders}'s to add to the response
     * @param httpStatus The {@link HttpStatus} to add to the response
     */
    public RestConsumerResponse(@JsonProperty("data")final T data,
                                @JsonProperty("notifications")final List<Notification> notifications,
                                @JsonProperty("httpHeaders") final HttpHeaders httpHeaders,
                                @JsonProperty("httpStatus") final HttpStatus httpStatus) {
        this.data = data;
        this.pagingCursor = null;
        this.notifications = notifications;
        this.httpHeaders = httpHeaders;
        this.httpStatus = httpStatus;
    }



    /**
     * Basic constructor with Paging for the class.
     *
     * @param data The response body
     * @param pagingCursor The {@link Paging}'s to add to the response
     * @param notifications The {@link Notification}'s to add to the response
     * @param httpHeaders The {@link HttpHeaders}'s to add to the response
     */
    public RestConsumerResponse(@JsonProperty("data")final T data,
                                @JsonProperty("paging") final Paging pagingCursor,
                                @JsonProperty("notifications")final List<Notification> notifications,
                                @JsonProperty("httpHeaders") final HttpHeaders httpHeaders) {
        this.data = data;
        this.pagingCursor = pagingCursor;
        this.notifications = notifications;
        this.httpHeaders = httpHeaders;
    }
    /**
     * Basic constructor with Paging for the class and httpStatus.
     *
     * @param data The response body
     * @param pagingCursor The {@link Paging}'s to add to the response
     * @param notifications The {@link Notification}'s to add to the response
     * @param httpHeaders The {@link HttpHeaders}'s to add to the response
     * @param httpStatus The {@link HttpStatus} to add to the response
     */

    @JsonCreator
    public RestConsumerResponse(@JsonProperty("data")final T data,
                                @JsonProperty("paging") final Paging pagingCursor,
                                @JsonProperty("notifications")final List<Notification> notifications,
                                @JsonProperty("httpHeaders") final HttpHeaders httpHeaders,
                                @JsonProperty("httpStatus") final HttpStatus httpStatus) {
        this.data = data;
        this.pagingCursor = pagingCursor;
        this.notifications = notifications;
        this.httpHeaders = httpHeaders;
        this.httpStatus = httpStatus;
    }

    /**
     * Transform {@link List} of {@link Notification}s. If {@link Notification} has field {@link Notification#getTransformResponse()}
     * set to true, new {@link Notification} will be created such that:
     * {@link Notification#getCode()} with the value of String representation of given {@link HttpStatus}, empty String if {@link HttpStatus} is null;
     * {@link Notification#getMessage()}, {@link Notification#getFieldName()} and {@link Notification#getMetadata()} from original {@link Notification};
     * {@link Notification#getUuid()} and {@link Notification#getTimestamp()} will be re-generated.
     *
     * @param notifications The {@link Notification}'s to add to the response
     * @param httpStatus The {@link HttpStatus} that should be set as error code
     * @return A {@link List} of {@link Notification}
     */
    private static List<Notification> transformNotification(final List<Notification> notifications, final HttpStatus httpStatus) {
        return notifications.stream()
                .map(notification ->
                        notification.getTransformResponse()
                                ? Notification.builder().transform(notification, httpStatus).build()
                                : notification)
                .collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public String getSiemTransactionData() {
        return ofNullable(data).map(Object::toString).orElse("");
    }
}
