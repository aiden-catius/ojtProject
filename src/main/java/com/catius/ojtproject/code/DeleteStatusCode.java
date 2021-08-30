package com.catius.ojtproject.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeleteStatusCode {
    TRUE("존재"),
    FALSE("삭제");

    private String description;

}
