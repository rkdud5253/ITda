package com.ssafy.itda.domain;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User : 어르신 정보")
public class User {

	@NotNull
	@ApiModelProperty(value = "어르신 ID")
	private int userId;
	@NotNull
	@ApiModelProperty(value = "어르신 이름")
	private String userName;
	
}
