/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerProfileDuplicateBvnException extends BaseException {

    public CustomerProfileDuplicateBvnException() {
        super(HttpStatus.BAD_REQUEST, "Profile with similar bvn already exists");
    }
}
