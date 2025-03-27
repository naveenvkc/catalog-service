package com.polarbookshop.catalog.shared.logging;

import org.slf4j.LoggerFactory;
import org.slf4j.ext.LoggerWrapper;

import java.util.function.Consumer;

import static com.polarbookshop.catalog.shared.logging.Logger.Level.*;

public class Logger extends LoggerWrapper{

    public static final String DEFAULT_APP_NAME = "defaultAppName";
    public static final String SIEM_EVENT_DATA_KEY = "siemEventDataKey";
    public static final String NOTIFICATION_KEY = "notification";

    Logger(final org.slf4j.Logger logger) {
        super(logger, Logger.class.getName());
    }

    public static Logger get(final Class<?> clazz) {
        org.slf4j.Logger delegate = LoggerFactory.getLogger(clazz);
        return new Logger(delegate);
    }

    public void metric(final Consumer<Event> consumer) {
        this.log(INFO, new Event(Type.metric()), consumer);
    }

    public void audit(final Consumer<Event> consumer) {
        this.log(INFO, new Event(Type.audit()), consumer);
    }

    public void security(final Consumer<Event> consumer) {
        this.log(INFO, new Event(Type.security()), consumer);
    }

    public void track(final Consumer<Event> consumer) {
        this.log(INFO, new Event(Type.track()), consumer);
    }

    public void trace(final Consumer<Event> consumer) {
        this.log(TRACE, new Event(), consumer);
    }

    public void debug(final Consumer<Event> consumer) {
        this.log(DEBUG, new Event(), consumer);
    }

    public void info(final Consumer<Event> consumer) {
        this.log(INFO, new Event(), consumer);
    }

    public void warn(final Consumer<Event> consumer) {
        this.log(WARN, new Event(), consumer);
    }

    public void error(final Consumer<Event> consumer) {
        this.log(ERROR, new Event(), consumer);
    }

    @SuppressWarnings("squid:S2629")
    private void log(final Level level, final Event event, final Consumer<Event> consumer) {
        if (level.enabled(this)) {
            consumer.accept(event);
        }
        switch (level) {
            case TRACE:
                this.trace(event.marker(), event.message(), event.args());
                break;
            case DEBUG:
                this.debug(event.marker(), event.message(), event.args());
                break;
            case INFO:
                this.info(event.marker(), event.message(), event.args());
                break;
            case WARN:
                this.warn(event.marker(), event.message(), event.args());
                break;
            case ERROR:
                this.error(event.marker(), event.message(), event.args());
                break;
            default:
                break;
        }
    }

    protected enum Level {
        TRACE, DEBUG, INFO, WARN, ERROR;

        public boolean enabled(final Logger logger) {
            switch (this) {
                case TRACE:
                    return logger.isTraceEnabled();
                case DEBUG:
                    return logger.isDebugEnabled();
                case INFO:
                    return logger.isInfoEnabled();
                case WARN:
                    return logger.isWarnEnabled();
                case ERROR:
                default:
                    return logger.isErrorEnabled();
            }
        }
    }
}
