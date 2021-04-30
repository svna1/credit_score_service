package com.demo.creditscore.controller.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    REQUEST_VALIDATION_FAILED("Request validation failed"), CONFLICT("");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
