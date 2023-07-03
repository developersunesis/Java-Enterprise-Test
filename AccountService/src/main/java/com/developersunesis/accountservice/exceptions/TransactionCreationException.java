/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TransactionCreationException extends BaseException {

    public TransactionCreationException(String accountNo) {
        super(HttpStatus.BAD_GATEWAY, "Error creating transaction for [" + accountNo + "]");
    }
}
