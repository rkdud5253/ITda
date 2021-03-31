package com.ssafy.itda.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User,Admin 정보")
public class UserAdmin {
    @NotNull
    @ApiModelProperty(value = "어르신 ID")
    private int userId;
    @NotNull
    @ApiModelProperty(value = "가족 ID")
    private String adminId;
}
