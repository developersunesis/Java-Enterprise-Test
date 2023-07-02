/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.controllers;

import com.developersunesis.accountservice.dtos.CustomerProfileDto;
import com.developersunesis.accountservice.dtos.ResponseDto;
import com.developersunesis.accountservice.exceptions.CustomerProfileDoesNotExistException;
import com.developersunesis.accountservice.exceptions.CustomerProfileDuplicateBvnException;
import com.developersunesis.accountservice.services.CustomerProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/profile")
public class CustomerProfileController {

    private final CustomerProfileService customerProfileService;

    @PostMapping
    public ResponseDto<CustomerProfileDto> create(@Validated @RequestBody final CustomerProfileDto customerProfileDto) throws CustomerProfileDuplicateBvnException {
        CustomerProfileDto savedCustomerProfileDto = customerProfileService.create(customerProfileDto);
        return ResponseDto.build(savedCustomerProfileDto, "Success");
    }

    @GetMapping("{id}")
    public ResponseDto<CustomerProfileDto> read(@PathVariable String id) throws CustomerProfileDoesNotExistException {
        CustomerProfileDto savedCustomerProfileDto = customerProfileService.get(id);
        return ResponseDto.build(savedCustomerProfileDto, "Success");
    }
}
