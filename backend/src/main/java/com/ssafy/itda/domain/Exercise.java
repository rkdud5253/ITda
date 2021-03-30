package com.ssafy.itda.domain;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Exercise : 체조 영상 정보")
public class Exercise {

	@NotNull
	@ApiModelProperty(value = "체조 ID")
	private int exerciseId;
	@NotNull
	@ApiModelProperty(value = "체조 영상 이름")
	private String exerciseName;
	@NotNull
	@ApiModelProperty(value = "체조 영상 url")
	private String exerciseUrl;

}
