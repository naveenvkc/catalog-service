package com.polarbookshop.catalog.model.consumer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

//@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@Generated
public class BookRequestModel {

    @JsonProperty("isbn")
    @NotBlank(message = "The book ISBN must be defined.")
    @Pattern(
            regexp = "^([0-9]{10}|[0-9]{13})$",
            message = "The ISBN format must be valid."
    )
    private String isbn;

    @JsonProperty("title")
    @NotBlank(message = "The book title must be defined.")
    private String title;

    @JsonProperty("author")
    @NotBlank(message = "The book author must be defined.")
    private String author;

    @JsonProperty("price")
    @NotNull(message = "The book price must be defined.")
    @Positive(
            message = "The book price must be greater than zero."
    )
    private BigDecimal price;

    public static BookRequestModel buildExample() {
        BookRequestModel requestObject = new BookRequestModel();
        requestObject.setIsbn("abc1234");
        requestObject.setTitle("testTitle");
        requestObject.setAuthor("testAuthor");
        requestObject.setPrice(new BigDecimal("10.00"));
        return requestObject;
    }

    public static BookRequestModel of(String isbn, String title, String author, BigDecimal price) {
        return new BookRequestModel(isbn, title, author, price);
    }
}
