/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.services.impl;

import com.developersunesis.accountservice.dtos.CreateTransactionDto;
import com.developersunesis.accountservice.dtos.CustomerAccountDto;
import com.developersunesis.accountservice.dtos.CustomerProfileDto;
import com.developersunesis.accountservice.exceptions.BaseException;
import com.developersunesis.accountservice.exceptions.CustomerCurrentAccountDoesNotExistException;
import com.developersunesis.accountservice.exceptions.TransactionCreationException;
import com.developersunesis.accountservice.services.AccountTransactionService;
import com.developersunesis.accountservice.services.CustomerAccountService;
import com.developersunesis.accountservice.services.CustomerProfileService;
import com.developersunesis.accountservice.services.entities.CustomerAccount;
import com.developersunesis.accountservice.services.repositories.CustomerAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private final CustomerProfileService customerProfileService;
    private final AccountTransactionService accountTransactionService;
    private final CustomerAccountRepository customerAccountRepository;

    @Transactional(rollbackFor = Exception.class)
    public CustomerAccountDto create(CustomerAccountDto customerAccountDto) throws BaseException {
        Optional<CustomerProfileDto> optionalCustomerProfileDto = Optional.of(customerProfileService
                .get(customerAccountDto.getCustomerId()));
        CustomerProfileDto customerProfileDto = optionalCustomerProfileDto.get();

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomerId(customerProfileDto.getId());
        customerAccount.setBalance(customerAccountDto.getInitialCredit());
        customerAccount.setStartingBalance(customerAccountDto.getInitialCredit());
        customerAccountRepository.save(customerAccount);

        if(customerAccount.getStartingBalance().doubleValue() > 0) {
            CreateTransactionDto createTransactionDto = CreateTransactionDto.builder()
                    .accountNo(customerAccount.getAccountNo()).amount(customerAccount.getStartingBalance())
                    .type("CREDIT").build();
            accountTransactionService.create(createTransactionDto)
                    .orElseThrow(() -> new TransactionCreationException(customerAccount.getAccountNo()));
        }

        BeanUtils.copyProperties(customerProfileDto, customerAccountDto);
        BeanUtils.copyProperties(customerAccount, customerAccountDto);
        return customerAccountDto;
    }

    @Override
    public CustomerAccountDto get(String id) throws BaseException {
        CustomerAccount customerAccount = customerAccountRepository.findById(id)
                .orElseThrow(() -> new CustomerCurrentAccountDoesNotExistException(id));
        Optional<CustomerProfileDto> optionalCustomerProfileDto = Optional.of(customerProfileService
                .get(customerAccount.getCustomerId()));
        CustomerProfileDto customerProfileDto = optionalCustomerProfileDto.get();

        CustomerAccountDto customerAccountDto = new CustomerAccountDto();
        BeanUtils.copyProperties(customerProfileDto, customerAccountDto);
        BeanUtils.copyProperties(customerAccount, customerAccountDto);

        return customerAccountDto;
    }
}
