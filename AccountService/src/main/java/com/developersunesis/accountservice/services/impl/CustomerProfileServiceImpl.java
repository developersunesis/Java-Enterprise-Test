/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.services.impl;

import com.developersunesis.accountservice.dtos.CustomerProfileDto;
import com.developersunesis.accountservice.exceptions.CustomerProfileDuplicateBvnException;
import com.developersunesis.accountservice.services.CustomerProfileService;
import com.developersunesis.accountservice.services.entities.CustomerProfile;
import com.developersunesis.accountservice.services.repositories.CustomerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;

    @Override
    public CustomerProfileDto create(CustomerProfileDto customerProfileDto) throws CustomerProfileDuplicateBvnException {
        if(customerProfileRepository.existsByBvn(customerProfileDto.getBvn()))
            throw new CustomerProfileDuplicateBvnException();

        // create new customer profile
        CustomerProfile customerProfile = new CustomerProfile();
        BeanUtils.copyProperties(customerProfileDto, customerProfile);
        customerProfileRepository.save(customerProfile);
        customerProfileDto.setId(customerProfile.getId());

        return customerProfileDto;
    }
}
