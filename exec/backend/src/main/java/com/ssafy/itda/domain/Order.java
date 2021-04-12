package com.ssafy.itda.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "명령어 정보")
public class Order {
    @NotNull
    @ApiModelProperty(value = "어르신 ID")
    private String hashIp;
    @NotNull
    @ApiModelProperty(value = "명령어")
    private String command;
}