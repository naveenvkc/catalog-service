package com.polarbookshop.catalog.model.consumer.rest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRequestModelValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var bookRequestModel =
                BookRequestModel.of("1234567890", "Title", "Author", new BigDecimal("9.90"));
        Set<ConstraintViolation<BookRequestModel>> violations = validator.validate(bookRequestModel);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnNotDefinedThenValidationFails() {
        var bookRequestModel =
                BookRequestModel.of("", "Title", "Author", new BigDecimal("9.90"));
        Set<ConstraintViolation<BookRequestModel>> violations = validator.validate(bookRequestModel);
        assertThat(violations).hasSize(2);
        List<String> constraintViolationMessages = violations.stream()
                .map(ConstraintViolation::getMessage).toList();
        assertThat(constraintViolationMessages)
                .contains("The book ISBN must be defined.")
                .contains("The ISBN format must be valid.");
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var bookRequestModel =
                BookRequestModel.of("a234567890", "Title", "Author", new BigDecimal("9.90"));
        Set<ConstraintViolation<BookRequestModel>> violations = validator.validate(bookRequestModel);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The ISBN format must be valid.");
    }

    @Test
    void whenTitleIsNotDefinedThenValidationFails() {
        var bookRequestModel =
                BookRequestModel.of("1234567890", "", "Author", new BigDecimal("9.90"));
        Set<ConstraintViolation<BookRequestModel>> violations = validator.validate(bookRequestModel);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book title must be defined.");
    }

    @Test
    void whenAuthorIsNotDefinedThenValidationFails() {
        var bookRequestModel =
                BookRequestModel.of("1234567890", "Title", "", new BigDecimal("9.90"));
        Set<ConstraintViolation<BookRequestModel>> violations = validator.validate(bookRequestModel);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book author must be defined.");
    }

    @Test
    void whenPriceIsNotDefinedThenValidationFails() {
        var bookRequestModel =
                BookRequestModel.of("1234567890", "Title", "Author", null);
        Set<ConstraintViolation<BookRequestModel>> violations = validator.validate(bookRequestModel);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be defined.");
    }

    @Test
    void whenPriceDefinedButZeroThenValidationFails() {
        var bookRequestModel =
                BookRequestModel.of("1234567890", "Title", "Author", new BigDecimal("0.00"));
        Set<ConstraintViolation<BookRequestModel>> violations = validator.validate(bookRequestModel);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be greater than zero.");
    }

    @Test
    void whenPriceDefinedButNegativeThenValidationFails() {
        var bookRequestModel =
                BookRequestModel.of("1234567890", "Title", "Author", new BigDecimal("-9.90"));
        Set<ConstraintViolation<BookRequestModel>> violations = validator.validate(bookRequestModel);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be greater than zero.");
    }
}
