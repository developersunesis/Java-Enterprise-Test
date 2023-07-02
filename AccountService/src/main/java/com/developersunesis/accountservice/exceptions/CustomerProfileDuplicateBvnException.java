/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.exceptions;

public class CustomerProfileDuplicateBvnException extends Exception {

    public CustomerProfileDuplicateBvnException() {
        super("Profile with similar bvn already exists");
    }
}
