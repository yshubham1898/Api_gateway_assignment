package com.example.Assignment.entity;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseDto<T> {

    private T data;
    private HttpStatus httpStatus;
    private Object error;

    public ResponseDto(T data, HttpStatus httpStatus, Object error) {
        this.data = data;
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus statusCode) {
        this.httpStatus = httpStatus;
    }

    public Object getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
