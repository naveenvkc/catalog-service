package com.polarbookshop.catalog.exception;

import java.io.Serial;

public class BookNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 3337718279498316900L;

    public BookNotFoundException(String isbn) {
        super("The book with ISBN " + isbn + " was not found.");
    }
}
