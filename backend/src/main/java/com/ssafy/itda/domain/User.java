package com.ssafy.itda.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User : 어르신 정보")
public class User {

	@ApiModelProperty(value = "어르신 ID")
	private int userId;
	@ApiModelProperty(value = "어르신 이름")
	private String userName;

}
