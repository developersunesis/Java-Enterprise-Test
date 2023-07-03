/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.services.impl;

import com.developersunesis.accountservice.dtos.AccountTransactionDto;
import com.developersunesis.accountservice.dtos.CreateTransactionDto;
import com.developersunesis.accountservice.services.AccountTransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    @Value("${transaction_service.endpoint:http://localhost:1011}")
    private String endpoint;
    private final RestTemplate restTemplate;

    public AccountTransactionServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    public Optional<AccountTransactionDto> create(CreateTransactionDto createTransactionDto){
        try {
            String url = MessageFormat.format("{0}/api/currentAccounts/{1}/transactions", endpoint,
                    createTransactionDto.getCustomerId());
            return Optional.ofNullable(restTemplate
                    .postForEntity(url, createTransactionDto, AccountTransactionDto.class).getBody());
        } catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
