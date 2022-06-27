package com.example.Assignment.Util;

import com.example.Assignment.entity.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T>ResponseEntity getResponse(T data, HttpStatus httpStatus, Object error) {
        return new ResponseEntity(new ResponseDto<T>(data, httpStatus, error), httpStatus);
    }
}
