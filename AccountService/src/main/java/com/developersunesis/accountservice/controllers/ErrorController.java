/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.controllers;

import com.developersunesis.accountservice.dtos.ResponseDto;
import com.developersunesis.accountservice.exceptions.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@Slf4j
@ControllerAdvice
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorController extends DefaultHandlerExceptionResolver {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> unresolvedError(Exception e){
        log.error("Unresolved Error", e);
        return ResponseEntity.badRequest().body(ResponseDto.build(null, e.getMessage()));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> baseException(BaseException e){
        log.error("Unresolved Error", e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(ResponseDto.build(null, e.getMessage()));
    }
}
