package com.polarbookshop.catalog.shared.logging;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serial;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import static com.polarbookshop.catalog.shared.utils.Preconditions.ann;
import static com.polarbookshop.catalog.shared.utils.Preconditions.check;

@NotThreadSafe
public class Type implements Marker {

    private static final String OPEN = "[ ";
    private static final String CLOSE = " ]";
    private static final String SEP = ", ";
    @Serial
    private static final long serialVersionUID = 3557645502121756805L;
    private final List<Marker> references = new CopyOnWriteArrayList<>();

    /**
     * Marker that is used for standard application log messages.
     */
    public static final String APPLICATION_TYPE = "APP";

    /**
     * Marker used to indicate that a event represents an error.
     */
    public static final String ERROR_TYPE = "ERROR";

    /**
     * Marker that can be used to mark a log message as a tracking event.
     * <p>
     * A "tracking" event is used for events which are involved in cross system
     * request correlation as implemented by the propeller tracing system.
     */
    public static final String TRACK_TYPE = "TRACK";

    /**
     * Marker that can be used to mark the log message as a security related event.
     */
    public static final String SECURITY_TYPE = "SECURITY";

    /**
     * Marker that can be used to mark the log message as an audit related event.
     */
    public static final String AUDIT_TYPE = "AUDIT";

    /**
     * Marker that can be used to mark the log message as an access event.
     */
    public static final String ACCESS_TYPE = "ACCESS";

    /**
     * Marker that can be used to mark the log message as a metric event.
     */
    public static final String METRIC_TYPE = "METRIC";

    /**
     * Marker that can be used to mark the log message as potentially containing
     * personally identifiable information.
     * <p>
     * This marker may be added in addition to any other marker, it's more like
     * a tag than anything else.
     */
    public static final Type SENSITIVE_TYPE = Type.named("SENSITIVE");

    @Getter
    private final String name;

    private Type(final String name) {
        check(!StringUtils.isBlank(name), "Argument [name] may not be null or empty");
        this.name = name;
    }

    public void sensitive() {
        this.add(SENSITIVE_TYPE);
    }

    @Override
    public void add(Marker reference) {
        ann(reference, "reference");

        if (!this.contains(reference) && !reference.contains(this)) {
            references.add(reference);
        }
    }

    @Override
    public boolean remove(Marker reference) {
        return references.remove(reference);
    }

    @Override
    public boolean hasChildren() {
        return hasReferences();
    }

    @Override
    public boolean hasReferences() {
        return !references.isEmpty();
    }

    @Override
    public Iterator<Marker> iterator() {
        return references.iterator();
    }

    @Override
    public boolean contains(Marker other) {
        ann(other, "other");

        if (this.equals(other)) {
            return true;
        }

        if (hasReferences()) {
            for (Marker ref : references) {
                if (ref.contains(other)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean contains(String name) {
        ann(name, "name");

        if (this.name.equals(name)) {
            return true;
        }

        if (hasReferences()) {
            for (Marker ref : references) {
                if (ref.contains(name)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Marker other)) {
            return false;
        }

        return name.equals(other.getName());
    }

    public int hashCode() {
        return name.hashCode();
    }

    public String toString() {
        if (!this.hasReferences()) {
            return this.getName();
        }

        Iterable<Marker> iterable = this::iterator;
        String refs = StreamSupport.stream(iterable.spliterator(), false)
                .map(Marker::getName)
                .collect(Collectors.joining(SEP));

        return this.getName() + ' ' + OPEN + refs + CLOSE;
    }

    /**
            * Return a new {@link Type} with the given {@code name}.
            *
            * @param name The name of the type to create.
            * @return A new {@link Type} instance
     */
    public static Type named(final String name) {
        return new Type(name);
    }

    public static Type app() {
        return named(APPLICATION_TYPE);
    }

    public static Type audit() {
        return named(AUDIT_TYPE);
    }

    public static Type metric() {
        return named(METRIC_TYPE);
    }

    public static Type security() {
        return named(SECURITY_TYPE);
    }

    public static Type access() {
        return named(ACCESS_TYPE);
    }

    public static Type track() {
        return named(TRACK_TYPE);
    }
}
