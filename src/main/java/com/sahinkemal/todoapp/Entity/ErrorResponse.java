package com.sahinkemal.todoapp.Entity;

public class ErrorResponse {
    private Integer statusCode;
    private Boolean success;
    private String message;

    public ErrorResponse(Integer statusCode, Boolean success, String message) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
    }
}
