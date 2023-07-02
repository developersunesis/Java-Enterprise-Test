/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerProfileDoesNotExistException extends BaseException {

    public CustomerProfileDoesNotExistException(String id) {
        super(HttpStatus.NOT_FOUND, "Profile [" + id + "] does not exist");
    }
}
