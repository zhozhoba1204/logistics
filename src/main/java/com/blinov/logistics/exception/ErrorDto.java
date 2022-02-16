package com.blinov.logistics.exception;

/**
 * Dto для представления при получении ошибки
 */
public class ErrorDto {
    private String error;

    public ErrorDto(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
