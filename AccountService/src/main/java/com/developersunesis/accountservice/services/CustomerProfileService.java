/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.services;

import com.developersunesis.accountservice.dtos.CustomerProfileDto;
import com.developersunesis.accountservice.exceptions.CustomerProfileDoesNotExistException;
import com.developersunesis.accountservice.exceptions.CustomerProfileDuplicateBvnException;

public interface CustomerProfileService {
    CustomerProfileDto create(CustomerProfileDto customerProfileDto) throws CustomerProfileDuplicateBvnException;

    CustomerProfileDto get(String id) throws CustomerProfileDoesNotExistException;
}
