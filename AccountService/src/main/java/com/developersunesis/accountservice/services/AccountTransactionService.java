/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.services;

import com.developersunesis.accountservice.dtos.AccountTransactionDto;
import com.developersunesis.accountservice.dtos.CreateTransactionDto;

import java.util.Optional;

public interface AccountTransactionService {
    Optional<AccountTransactionDto> create(CreateTransactionDto createTransactionDto);
}
