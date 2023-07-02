/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.services.repositories;

import com.developersunesis.accountservice.services.entities.CustomerProfile;
import org.springframework.data.repository.CrudRepository;

public interface CustomerProfileRepository extends CrudRepository<CustomerProfile, String> {
    boolean existsByBvn(String bvn);
}
