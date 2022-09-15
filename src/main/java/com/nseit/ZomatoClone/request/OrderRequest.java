package com.nseit.ZomatoClone.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Integer bookId;
    private Integer userId;
}
