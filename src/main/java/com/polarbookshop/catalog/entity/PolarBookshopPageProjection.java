package com.polarbookshop.catalog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public interface PolarBookshopPageProjection {

    @JsonProperty("isbn")
    String getIsbn();

    @JsonProperty("title")
    String getTitle();

    @JsonProperty("author")
    String getAuthor();

    @JsonProperty("price")
    BigDecimal getPrice();
}
