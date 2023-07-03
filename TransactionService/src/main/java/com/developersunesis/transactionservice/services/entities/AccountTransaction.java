/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.transactionservice.services.entities;

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
import java.util.UUID;

@Getter
@Setter
@Entity(name = "account_transactions")
public class AccountTransaction {
    @Id
    private String id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String accountNo;
    @Column(nullable = false, length = 5)
    private String currency;
    @Column(nullable = false)
    private String customerId;
    @Column(nullable = false)
    private BigDecimal amount;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    @PrePersist
    public void setId(){
        this.id = UUID.randomUUID().toString();
    }
}
