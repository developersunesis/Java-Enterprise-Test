/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.services;

import com.developersunesis.accountservice.dtos.CustomerAccountDto;
import com.developersunesis.accountservice.exceptions.BaseException;

public interface CustomerAccountService {
    CustomerAccountDto create(CustomerAccountDto customerAccountDto) throws BaseException;

    CustomerAccountDto get(String id) throws BaseException;
}
