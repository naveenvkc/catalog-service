package com.polarbookshop.catalog.shared.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Paging implements Serializable {
    @Serial
    private static final long serialVersionUID = -3118589143160191412L;

    /**
     * Next cursor key for the set of entries retrieved. This value is left absent when there are no more values to return
     */
    private String nextCursorKey;

    /**
     * Create a new {@link Paging} with the given details.
     *
     * @param nextCursorKey     The next cursor key
     */
    @JsonCreator
    public Paging(final String nextCursorKey) {
        this.nextCursorKey = nextCursorKey;
    }
}
