package com.polarbookshop.catalog.shared.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.polarbookshop.catalog.shared.utils.ErrorMessageUtils;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import static com.polarbookshop.catalog.shared.utils.Preconditions.ann;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = Notification.NotificationBuilder.class)
public class Notification {
    static final String UNDERSCORE = "_";
    static final String COMPONENT_NAME = "componentName";
    static final String ERROR_NUMBER = "errorNumber";
    /**
     * The message which describes this notification.
     */
    String message;

    Severity severity;

    @NonNull
    String code;

    /**
     * The field name in request this notification is related to.
     */
    @JsonProperty("field_name")
    String fieldName;

    /**
     * The category of this notification as a {@link HttpStatus}.
     *
     * @deprecated Going to be removed by error handling design.
     */
    @Deprecated
    HttpStatus category;

    /**
     * The message which explains this notification.
     *
     * @deprecated Going to be removed by error handling design.
     */
    @Deprecated
    String description;

    /**
     * A suggested action following this notification.
     *
     * @deprecated Going to be removed by error handling design.
     */
    @Deprecated
    String action;

    /**
     * The metadata of the notification which is a map of key value pairs containing
     * extra information that may be relevant to the notification. This information
     * should be informational and not be used by consumers to infer action to
     * be taken when this notification occurs.
     * Can be built with entire map by calling {@link NotificationBuilder#metadata},
     * or with each entry of the map by calling {@link NotificationBuilder#metadataEntry(String, Object)}.
     */
    @Singular("metadataEntry")
    Map<String, Object> metadata;

    /**
     * The identifier of the unique instance of this notification.
     * By default it's generated by {@link UUID#randomUUID()}.
     */
    @Builder.Default
    UUID uuid = UUID.randomUUID();

    /**
     * The {@link Instant} this notification occurred.
     * By default it's generated by {@link Instant#now()}.
     */
    @Builder.Default
    Instant timestamp = Instant.now();

    /**
     * Whether the response need to be transformed. By default the value is false, when set to true,
     * it suggests that the notification need to be transformed for consumer response - error code should be overwritten
     * to {@link HttpStatus}.
     */
    @JsonIgnore
    @Builder.Default
    Boolean transformResponse = false;

    /**
     * An enumeration of the known severity levels that a notification
     * can have including a {@link #UNSPECIFIED} level for instances
     * where the others may not be applicable.
     */
    public enum Severity {
        ERROR,
        WARNING,
        INFO,
        UNSPECIFIED;

        /**
         * Get the {@link Severity} from the given {@code char}. This
         * method return the severity with the same first letter as the
         * given {@code char} or {@link #UNSPECIFIED} if not found.
         *
         * @param character The character to match
         * @return The correct {@link Severity} constant
         */
        public static Severity of(final char character) {
            char c = ann(character, "character");
            switch (c) {
                case 'E':
                    return ERROR;
                case 'W':
                    return WARNING;
                case 'I':
                    return INFO;
                default:
                    return UNSPECIFIED;
            }
        }

        /**
         * Translate {@link Severity} to character. In case of {@link #UNSPECIFIED},
         * return 'U'.
         *
         * @param severity Severity to match
         * @return character representation for given severity
         */
        public static char toChar(final Severity severity) {
            switch (severity) {
                case ERROR:
                    return 'E';
                case WARNING:
                    return 'W';
                case INFO:
                    return 'I';
                //Leave default as UNSPECIFIED in this case
                default:
                    return 'U';
            }
        }
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class NotificationBuilder {
       /**
         * Overwrite original Lombok code builder.
         * Translate and set severity from first character from the error code, and set the message from resource bundle.
         *
         * @deprecated use {@link #code(Severity, String, String)} instead.
         */
        @Deprecated
        public NotificationBuilder code(final String code) {
            this.severity = getSeverity(code);
            this.message = ErrorMessageUtils.getMessage(code);
            this.code = code;
            return this;
        }

        /**
         * Extension of {@link #code(String)} to use arguments to replace tokens in message resource bundle.
         *
         * @deprecated use {@link #code(Severity, String, String, String[])} instead.
         */
        @Deprecated
        public NotificationBuilder code(final String code, final String[] messageArgs) {
            this.severity = getSeverity(code);
            this.message = ErrorMessageUtils.getMessage(code, messageArgs);
            this.code = code;
            return this;
        }

        /**
         * Overwrite original Lombok code builder.
         * Construct error code from severity, componentName and errorNumber and set the message from resource bundle.
         */
        public NotificationBuilder code(final Severity severity, final String componentName, final String errorNumber) {
            String code = Severity.toChar(severity) + prepareComponentName(componentName).concat(ann(errorNumber, ERROR_NUMBER));
            this.code = code;
            this.severity = Severity.ERROR;
            this.message = ErrorMessageUtils.getMessage(code);
            return this;
        }

        /**
         * Extension of {@link #code(Severity, String, String)} to use arguments to replace tokens in message resource bundle.
         */
        public NotificationBuilder code(final Severity severity, final String componentName, final String errorNumber, final String[] messageArgs) {
            String code = Severity.toChar(severity) + prepareComponentName(componentName).concat(ann(errorNumber, ERROR_NUMBER));
            this.code = code;
            this.severity = severity;
            this.message = ErrorMessageUtils.getMessage(code, messageArgs);
            return this;
        }

        /**
         * Extension of {@link #code(Severity, String, String)} to use arguments to replace tokens in message resource bundle, also use
         * default {@link Severity#ERROR}.
         */
        public NotificationBuilder code(final String componentName, final String errorNumber, final String[] messageArgs) {
            String code = NotificationConstants.ERROR_SEVERITY_STRING + prepareComponentName(componentName).concat(ann(errorNumber, ERROR_NUMBER));
            this.code = code;
            this.severity = Severity.ERROR;
            this.message = ErrorMessageUtils.getMessage(code, messageArgs);
            return this;
        }

        /**
         * Overloaded code builder. translate and set severity from first character from the error code, and set the message
         * from resource bundle while replacing multiple parameters in message with given args.
         */
        public NotificationBuilder notification(final Notification notification) {
            this.code = notification.code;
            this.message = notification.getMessage();
            this.severity = notification.getSeverity();
            this.fieldName = notification.getFieldName();
            this.category = notification.getCategory();
            this.description = notification.getDescription();
            this.action = notification.getAction();
            this.metadata(notification.getMetadata());
            return this;
        }

        /**
         * Transform a given {@link Notification} to {@link NotificationBuilder}, with code overwritten to {@link String}
         * representation of {@link HttpStatus}. Override the code to empty {@link String} if {@link HttpStatus} is null.
         * Only copies code, message, fieldName and metaData from original {@link Notification}.
         * Protected access level, intended to be used internally only.
         */
        NotificationBuilder transform(final Notification notification, final HttpStatus httpStatus) {
            this.code =  httpStatus != null ? String.valueOf(httpStatus.value()) : "";
            this.message = notification.getMessage();
            this.fieldName = notification.getFieldName();
            this.severity = notification.getSeverity();
            this.metadata(notification.getMetadata());
            return this;
        }

            /**
             * Get the {@link Severity} of this {@link Notification}.
             *
             * @return The {@link Severity}
             */
        private Severity getSeverity(final String code) {
            if (code == null || code.isEmpty()) {
                throw new IllegalArgumentException("Notification error code should not be null or empty.");
            }
            else {
                return Severity.of(code.charAt(0));
            }
        }

        /**
         * Prepare componentName by appending "_" in the front and at the end of the input, if not already present.
         * *
         *
         * @return The {@link String} formatted component name.
         * @throws IllegalArgumentException when input is null, empty string or full of '_' chars.
         */
        private String prepareComponentName(final String componentName) {
            if (componentName == null || componentName.isEmpty() || componentName.replaceAll(UNDERSCORE, "").isEmpty()) {
                throw new IllegalArgumentException("componentName should not be null or empty or just underscore");
            }
            else if (!String.valueOf(componentName.charAt(0)).equals(UNDERSCORE) && !String.valueOf(componentName.charAt(componentName.length() - 1)).equals(UNDERSCORE)) {
                return UNDERSCORE + componentName + UNDERSCORE;
            }
            else if (String.valueOf(componentName.charAt(0)).equals(UNDERSCORE) && String.valueOf(componentName.charAt(componentName.length() - 1)).equals(UNDERSCORE)) {
                return componentName;
            }
            else if (String.valueOf(componentName.charAt(0)).equals(UNDERSCORE)) {
                return componentName + UNDERSCORE;
            }
            else {
                return UNDERSCORE + componentName;
            }
        }
    }
}
