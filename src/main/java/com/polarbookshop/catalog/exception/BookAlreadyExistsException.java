package com.polarbookshop.catalog.exception;

import java.io.Serial;

public class BookAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 349720051433207688L;

    public BookAlreadyExistsException(String isbn) {
        super("A book with ISBN " + isbn + " already exists.");
    }
}
