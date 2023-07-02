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

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "customer_profiles")
public class CustomerProfile {
    @Id
    private String id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 500)
    private String address;
    @Column(length = 20, unique = true)
    private String bvn;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    @PrePersist
    public void setId(){
        this.id = UUID.randomUUID().toString();
    }
}
