package com.catius.ojtproject.exception;

import com.catius.ojtproject.dto.DeviceErrorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class DeviceExceptionHandler {


    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler
    public DeviceErrorDetail handleException(DeviceException e,
                                             HttpServletRequest request){
        log.error("errorCode: {},  url: {}, message: {},",
                e.getDeviceErrorCode(), request.getRequestURI(),e.getDetailMessage());

        return DeviceErrorDetail.builder()
                .errorCode(e.getDeviceErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            //@validation Error
            MethodArgumentNotValidException.class
    })
    public DeviceErrorDetail handleBadRequest(
            Exception e, HttpServletRequest request
    ) {
        log.error("errorCode: {},  url: {}, message: {},",request.getRequestURI(),e.getMessage());

        return DeviceErrorDetail.builder()
                .errorCode(DeviceErrorCode.INVALID_REQUEST)
                .errorMessage(DeviceErrorCode.INVALID_REQUEST.getMessage())
                .build();

    }



    @ExceptionHandler(Exception.class)
    public DeviceErrorDetail handleException(
            Exception e, HttpServletRequest request
    ) {
        log.error("errorCode: {},  url: {}, message: {},",request.getRequestURI(),e.getMessage());

        return DeviceErrorDetail.builder()
                .errorCode(DeviceErrorCode.INVALID_REQUEST)
                .errorMessage(DeviceErrorCode.INVALID_REQUEST.getMessage())
                .build();

    }


}
