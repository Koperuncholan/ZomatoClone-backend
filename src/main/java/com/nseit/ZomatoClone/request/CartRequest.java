package com.nseit.ZomatoClone.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {

    private Integer userId;
    private Integer bookId;
    private Integer count;

}
