package com.ssafy.itda.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Exercise : 체조 영상 정보")
public class Exercise {

	@ApiModelProperty(value = "체조 ID")
	private int exercise_id;
	@ApiModelProperty(value = "체조 영상 이름")
	private String exercise_name;
	@ApiModelProperty(value = "체조 영상 url")
	private String exercise_url;

}
