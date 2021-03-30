package com.ssafy.itda.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WrongQuestion {

    @NotNull
    @ApiModelProperty(value = "어르신 ID")
    private int userId;
    @NotNull
    @ApiModelProperty(value = "문제 ID")
    private int questionId;
}
