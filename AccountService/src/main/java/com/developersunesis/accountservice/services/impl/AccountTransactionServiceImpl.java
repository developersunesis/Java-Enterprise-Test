/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.services.impl;

import com.developersunesis.accountservice.dtos.AccountTransactionDto;
import com.developersunesis.accountservice.dtos.CreateTransactionDto;
import com.developersunesis.accountservice.services.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountTransactionServiceImpl implements AccountTransactionService {

    public Optional<AccountTransactionDto> create(CreateTransactionDto createTransactionDto){
        throw new UnsupportedOperationException();
    }
}
