/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.transactionservice.services.repositories;

import com.developersunesis.transactionservice.services.entities.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface AccountTransactionRepository extends CrudRepository<AccountTransaction, String> {
    Page<AccountTransaction> findAllByCustomerIdAndAccountNo(String customerId, String accountNo,
                                                             Pageable pageable);
}
