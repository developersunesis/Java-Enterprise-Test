/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.transactionservice.services;

import com.developersunesis.transactionservice.dtos.AccountTransactionDto;
import com.developersunesis.transactionservice.dtos.PagedResponseDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AccountTransactionService {
    AccountTransactionDto create(AccountTransactionDto accountTransactionDto);

    PagedResponseDto<List<AccountTransactionDto>> findAllTransactions(String customerId, String accountNo, PageRequest of);
}
