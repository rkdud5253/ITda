package com.ssafy.itda.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DailyReport : 데일리 보고서 정보")
public class DailyReport {

	@ApiModelProperty(value = "보고서 날짜")
	private String reportDate;
	@ApiModelProperty(value = "어르신 ID")
	private int userId;
	@ApiModelProperty(value = "체조 ID")
	private int exerciseId;
	@ApiModelProperty(value = "체조 정확도")
	private String exerciseAccuracy;
	@ApiModelProperty(value = "문제 1번")
	private int question1Id;
	@ApiModelProperty(value = "문제 2번")
	private int question2Id;
	@ApiModelProperty(value = "문제 3번")
	private int question3Id;
	@ApiModelProperty(value = "문제 4번")
	private int question4Id;
	@ApiModelProperty(value = "문제 5번")
	private int question5Id;
	@ApiModelProperty(value = "맞은 문제 번호")
	private String right_numbers;
	@ApiModelProperty(value = "틀린 문제 번호")
	private String wrong_numbers;

}
