/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerCurrentAccountDoesNotExistException extends BaseException {

    public CustomerCurrentAccountDoesNotExistException(String accountNo) {
        super(HttpStatus.NOT_FOUND, "Current account [" + accountNo + "] does not exist");
    }
}
