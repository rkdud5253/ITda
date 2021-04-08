package com.ssafy.itda.domain;

import lombok.Data;

@Data
public class ErrorMessage {

    private final String message;
    private final Integer status;

}