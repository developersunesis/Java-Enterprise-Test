/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.services.repositories;

import com.developersunesis.accountservice.services.entities.CustomerAccount;
import org.springframework.data.repository.CrudRepository;

public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, String> {
}
