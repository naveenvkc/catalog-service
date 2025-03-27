package com.polarbookshop.catalog.model.consumer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseModel {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("price")
    private BigDecimal price;

    public static BookResponseModel buildExample() {
        BookResponseModel responseObject = new BookResponseModel();
        responseObject.setId(1L);
        responseObject.setIsbn("1231231231");
        responseObject.setTitle("testTitle");
        responseObject.setAuthor("testAuthor");
        responseObject.setPrice(new BigDecimal("10.00"));
        return responseObject;
    }

    public static BookResponseModel of(Long id, String isbn, String title, String author, BigDecimal price) {
        return new BookResponseModel(id, isbn, title, author, price);
    }
}
