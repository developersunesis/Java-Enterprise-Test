/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.transactionservice.controllers;

import com.developersunesis.transactionservice.dtos.AccountTransactionDto;
import com.developersunesis.transactionservice.dtos.PagedResponseDto;
import com.developersunesis.transactionservice.dtos.ResponseDto;
import com.developersunesis.transactionservice.services.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/currentAccounts/{customerId}/transactions")
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;

    @PostMapping
    public ResponseDto<AccountTransactionDto> create(@PathVariable String customerId,
                                                     @Validated @RequestBody AccountTransactionDto accountTransactionDto){
        accountTransactionDto.setCustomerId(customerId);
        AccountTransactionDto savedAccountTransactionDto = accountTransactionService.create(accountTransactionDto);
        return ResponseDto.build(savedAccountTransactionDto, "Success");
    }

    @GetMapping
    public PagedResponseDto<List<AccountTransactionDto>> getAll(@PathVariable String customerId, @RequestParam String accountNo,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "50") int size) {
        return accountTransactionService.findAllTransactions(customerId, accountNo, PageRequest.of(page, size));
    }
}
