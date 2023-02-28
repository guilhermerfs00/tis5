package com.puc.ticketin.domain.exception;


import lombok.Getter;

import java.util.List;

import static com.puc.ticketin.domain.exception.EventException.Reason.FILE_IMPORT_ERROR;

@Getter
public class EventException extends RuntimeException {

    private Reason reason;
    private List<String> notifications;


    public EventException(final List<String> validationMessage) {
        super(String.join(", ", validationMessage));
        this.notifications = validationMessage;
        this.reason = FILE_IMPORT_ERROR;
    }

    public EventException(final String message, final Reason reason) {
        super(message);
        this.notifications = List.of();
        this.reason = reason;
    }

    public EventException(final String message, final Reason reason, final Throwable e) {
        super(message, e);
        this.notifications = List.of();
        this.reason = reason;
    }

    public EventException(final String description) {
        super(description);
    }

    public EventException(final String message, final Throwable e) {
        super(message, e);
        this.notifications = List.of();
        this.reason = Reason.UNEXPECTED_ERROR;
    }

    public enum Reason {
        EVENT_NOT_FOUND,
        FILE_IMPORT_ERROR,
        UNEXPECTED_ERROR
    }
}
