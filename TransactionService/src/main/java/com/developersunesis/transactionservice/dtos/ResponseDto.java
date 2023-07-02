/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.transactionservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {
    private T data;
    private String message;

    public static <T> ResponseDto<T> build(T data, String message) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.message = message;
        responseDto.data = data;
        return responseDto;
    }
}
