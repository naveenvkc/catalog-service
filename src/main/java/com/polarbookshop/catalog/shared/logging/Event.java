package com.polarbookshop.catalog.shared.logging;

import com.fasterxml.jackson.annotation.JsonValue;
import com.polarbookshop.catalog.shared.rest.Notification;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.*;
import java.util.function.Consumer;

import static com.polarbookshop.catalog.shared.utils.Preconditions.ann;

public class Event {

    private Map<String, Object> data = new HashMap<>();
    private Type marker;
    private String message = "";
    private Object[] args;
    private Throwable throwable;

    public Event() {
        this(Type.APPLICATION_TYPE);
    }

    public Event(final String type) {
        this(Type.named(type));
    }

    public Event(final Type type) {
        this.marker = type;
    }

    public Event type(final String type) {
        ann(type, "type");
        return type(Type.named(type));
    }

    public Event type(final Type type) {
        // copy any child markers into the new parent type marker
        this.marker.iterator().forEachRemaining(type::add);
        this.marker = type;

        return this;
    }

    public Event tag(final String tag) {
        this.marker.add(MarkerFactory.getDetachedMarker(tag));
        return this;
    }

    public Event tag(final Marker marker) {
        this.marker.add(marker);
        return this;
    }

    public Event message(final String message) {
        this.message = message;
        return this.siem();
    }

    public String message() {
        var formatted = getFormatted();
        if (this.data.isEmpty()) return formatted;
        String modified = formatted + " data: " + this.data;
        return modified.trim();
    }

    public Event message(final String message, final Object... args) {
        this.message = message;
        this.args = args;
        return this.siem();
    }

    private @NotNull String getFormatted() {
        return String.format("%s%s-%s: %s", "EMP", "BGQH", "catalog-application", this.message);
    }

    public Event with(final String key, final Object value) {
        this.data.put(key, value);
        return this;
    }

    public Event with(final SiemBaseEventData value) {
        this.data.put(Logger.SIEM_EVENT_DATA_KEY, value);
        return this;
    }

    public Event with(final Notification notification) {
        this.data.put(Logger.NOTIFICATION_KEY, notification);
        return this;
    }

    /**
     * Add a {@link List} of {@link Notification}s to this event.
     *
     * @param notifications The detail value of {@link Notification}s
     * @return {@code this}
     */
    public Event with(final List<Notification> notifications) {
        this.data.put(Logger.NOTIFICATION_KEY, notifications);
        return this;
    }

    /**
     * Add an detail element to this event by populating the given {@link Map}
     * which will be placed under the given key. Useful when constructing nested
     * details.
     *
     * @param key      The key of the detail item
     * @param consumer A consumer used to populate the map
     * @return {@code this}
     */
    public Event with(final String key, final Consumer<Map<String, Object>> consumer) {
        Map<String, Object> map = new HashMap<>();
        consumer.accept(map);
        return this.with(key, map);
    }

    /**
     * Return an {@link Optional} which may contain the {@link Throwable}
     * attached to this event.
     *
     * @return An {@link Optional} {@link Throwable}
     */
    public Optional<Throwable> throwable() {
        return Optional.ofNullable(throwable);
    }

    /**
     * Add a {@link Throwable} to this event.
     *
     * @param throwable The {@link Throwable}
     * @return {@code this}
     */
    public Event throwable(final Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    /**
     * Mark this event as potentially containing sensitive information.
     *
     * @return {@code this}
     */
    public Event sensitive() {
        this.tag(Markers.SENSITIVE);
        return this;
    }

    public Event siem() {
        this.tag(Markers.SIEMEVENT);
        return this;
    }

    /**
     * Get the arguments of this event that should be sent to the log
     * system. This is a composition of the message arguments and
     * {@code this}
     *
     * @return A array of log arguments
     */
    public Object[] args() {
        return ArrayUtils.addAll(this.args, this, this.throwable);
    }


    /**
     * Get an {@link Optional} potentially containing the value of the
     * specified key if one exists in the {@link #data()}.
     *
     * @param key The key of the data
     * @return An {@link Optional} value
     */
    public Optional<Object> get(final String key) {
        return Optional.ofNullable(this.data.get(key));
    }

    /**
     * Return a {@link Map} containing the extra data attached to this event.
     * The returned map is <b>unmodifiable</b>.
     *
     * @return A java.util.Collections.UnmodifiableMap of this event's data
     */
    @JsonValue
    public Map<String, Object> data() {
        return Collections.unmodifiableMap(this.data);
    }

    /**
     * The {@link Marker} to be used when logging this event.
     *
     * @return The {@link Marker} to add to the log.
     */
    public Marker marker() {
        return this.marker;
    }
}
