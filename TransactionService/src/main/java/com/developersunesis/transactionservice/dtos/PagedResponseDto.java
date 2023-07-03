/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.transactionservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedResponseDto<T> extends ResponseDto<T> {
    private int page;
    private int size;
    private boolean hasMore;
}
