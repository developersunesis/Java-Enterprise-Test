/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.controllers;

import com.developersunesis.accountservice.dtos.CustomerProfileDto;
import com.developersunesis.accountservice.dtos.ResponseDto;
import com.developersunesis.accountservice.exceptions.CustomerProfileDuplicateBvnException;
import com.developersunesis.accountservice.services.CustomerProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class CustomerProfileController {

    private final CustomerProfileService customerProfileService;

    @PostMapping("profile")
    public ResponseDto<CustomerProfileDto> create(@Validated @RequestBody final CustomerProfileDto customerProfileDto) throws CustomerProfileDuplicateBvnException {
        CustomerProfileDto savedCustomerProfileDto = customerProfileService.create(customerProfileDto);
        return ResponseDto.build(savedCustomerProfileDto, "Success");
    }
}
