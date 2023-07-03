/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.transactionservice.services.impl;

import com.developersunesis.transactionservice.dtos.AccountTransactionDto;
import com.developersunesis.transactionservice.dtos.PagedResponseDto;
import com.developersunesis.transactionservice.services.AccountTransactionService;
import com.developersunesis.transactionservice.services.entities.AccountTransaction;
import com.developersunesis.transactionservice.services.repositories.AccountTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransactionServiceImpl implements AccountTransactionService {

    private final AccountTransactionRepository accountTransactionRepository;

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto) {
        AccountTransaction accountTransaction = new AccountTransaction();
        BeanUtils.copyProperties(accountTransactionDto, accountTransaction);
        accountTransactionRepository.save(accountTransaction);

        BeanUtils.copyProperties(accountTransaction, accountTransactionDto);
        return accountTransactionDto;
    }

    @Override
    public PagedResponseDto<List<AccountTransactionDto>> findAllTransactions(String customerId, String accountNo, PageRequest pageRequest) {
        Page<AccountTransaction> accountTransactionPage = accountTransactionRepository
                .findAllByCustomerIdAndAccountNo(customerId, accountNo, pageRequest);
        PagedResponseDto<List<AccountTransactionDto>> arrayListPagedResponseDto = new PagedResponseDto<>();
        arrayListPagedResponseDto.setMessage("Success");
        arrayListPagedResponseDto.setPage(pageRequest.getPageNumber());
        arrayListPagedResponseDto.setSize(pageRequest.getPageSize());

        List<AccountTransactionDto> accountTransactionDtoList = accountTransactionPage.get().map(accountTransaction -> {
            AccountTransactionDto accountTransactionDto = new AccountTransactionDto();
            BeanUtils.copyProperties(accountTransaction, accountTransactionDto);
            return accountTransactionDto;
        }).toList();
        arrayListPagedResponseDto.setData(accountTransactionDtoList);
        return arrayListPagedResponseDto;
    }
}
