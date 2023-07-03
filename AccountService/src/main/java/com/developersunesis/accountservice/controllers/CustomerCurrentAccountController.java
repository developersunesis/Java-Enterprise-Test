/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.controllers;

import com.developersunesis.accountservice.dtos.CustomerAccountDto;
import com.developersunesis.accountservice.dtos.ResponseDto;
import com.developersunesis.accountservice.exceptions.BaseException;
import com.developersunesis.accountservice.services.CustomerAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/currentAccounts")
public class CustomerCurrentAccountController {

    private final CustomerAccountService customerAccountService;

    @PostMapping
    public ResponseDto<CustomerAccountDto> create(@Validated @RequestBody CustomerAccountDto customerAccountDto) throws BaseException {
        CustomerAccountDto savedCustomerAccountDto = customerAccountService.create(customerAccountDto);
        return ResponseDto.build(savedCustomerAccountDto, "Success");
    }

    @GetMapping("{accountNo}")
    public ResponseDto<CustomerAccountDto> read(@PathVariable String accountNo) throws BaseException {
        CustomerAccountDto savedCustomerAccountDto = customerAccountService.get(accountNo);
        return ResponseDto.build(savedCustomerAccountDto, "Success");
    }
}
