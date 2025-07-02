package com.github.gescof.springboottestingconcept.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void shouldHandleNotFoundException() {
        // Arrange
        NotFoundException exception = new NotFoundException("message");
        ErrorMessage expected = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        // Act
        ErrorMessage result = globalExceptionHandler.handleNotFoundException(exception);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
