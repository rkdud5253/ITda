package com.ssafy.itda.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Admin : 가족 정보")
public class Admin {
    @NotNull
    @ApiModelProperty(value = "가족 ID")
    private int adminId;
    @NotNull
    @ApiModelProperty(value = "가족 비밀번호")
    private String adminPwd;
    @ApiModelProperty(value = "가족 E-mail")
    private String adminEmail;
    @NotNull
    @ApiModelProperty(value = "가족 이름")
    private String adminName;
    @ApiModelProperty(value = "가족 번호")
    private String adminPhone;
}
