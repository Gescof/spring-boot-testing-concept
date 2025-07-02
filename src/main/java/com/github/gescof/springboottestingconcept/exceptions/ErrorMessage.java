package com.github.gescof.springboottestingconcept.exceptions;

import org.springframework.http.HttpStatus;

public record ErrorMessage(HttpStatus httpStatus, String message) {
}
