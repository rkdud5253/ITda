package com.ssafy.itda.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WrongQuestion {

    @NotNull
    @ApiModelProperty(value = "어르신 ID")
    private String userId;
    @NotNull
    @ApiModelProperty(value = "가족 ID")
    private String adminId;
}
