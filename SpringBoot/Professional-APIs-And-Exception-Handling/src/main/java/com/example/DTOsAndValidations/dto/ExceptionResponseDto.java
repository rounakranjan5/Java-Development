package com.example.DTOsAndValidations.dto;

import java.time.LocalDateTime;

public class ExceptionResponseDto {

    private LocalDateTime localDateTime;
    private String message;
    private String Path;
    private int statusCode;
    private String error;

    public ExceptionResponseDto(LocalDateTime localDateTime, String message, String path, int statusCode, String error) {
        this.localDateTime = localDateTime;
        this.message = message;
        Path = path;
        this.statusCode = statusCode;
        this.error = error;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
