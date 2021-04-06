package com.ssafy.itda.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "로그인 가능한 관계")
public class AccessCheck {
    @NotNull
    @ApiModelProperty(value = "어르신 이름")
    private String userName;
    @NotNull
    @ApiModelProperty(value = "가족 ID")
    private int adminId;
}
