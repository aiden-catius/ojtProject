package com.catius.ojtproject.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    ACTIVE("활성"),
    INACTIVE("비활성");

    private String description;

}
