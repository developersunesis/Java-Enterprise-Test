/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.services.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Getter
@Setter
@Entity(name = "customer_accounts")
public class CustomerAccount {
    @Id
    private String accountNo;
    @Column(nullable = false)
    private String customerId;
    @Column(nullable = false)
    private BigDecimal startingBalance = BigDecimal.ZERO;
    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    /**
     * This generates an account number for each account created between certain origin and bound.
     * There's a tendency of this algorithm clashing, but this probability is very low which makes it an OK-solution
     * for this current assessment. It could be further improved by using auto-increment if the business cares about
     * keeping account number order
     */
    @PrePersist
    public void setAccountNo() {
        this.accountNo = String.valueOf(new Random().nextInt(99999999, 999999999));
    }
}
