package com.polarbookshop.catalog.shared.utils;

import lombok.experimental.UtilityClass;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.function.Predicate;

@UtilityClass
public class Preconditions {

    public static void check(final boolean expression, final String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> T check(final T value, final Predicate<T> predicate, final String message) {
        if (!predicate.test(value)) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static <T> T nn(final T value, final String message) {
        return check(value, Objects::nonNull, message);
    }

    public static <T> T ann(final T value, final String argument) {
        return check(value, Objects::nonNull, "Argument [" + argument + "] may not be null");
    }

    public static <T> T cast(final Class<T> type, final Object value, final String message, final Object... args) {
        if (!type.isInstance(value)) {
            throw new IllegalArgumentException(MessageFormat.format(message, args));
        }
        return type.cast(value);
    }

    public static <T> T cast(final Class<T> type, final Object value) {
        return cast(type, value, "Expected an instance of %s", type);
    }

    public static <T> T state(final T value, final Predicate<T> test, final String message) {
        if (!test.test(value)) {
            throw new IllegalStateException(message);
        }
        return value;
    }
}
