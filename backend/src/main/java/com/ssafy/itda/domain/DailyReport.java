package com.ssafy.itda.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DailyReport : 데일리 보고서 정보")
public class DailyReport {

	@ApiModelProperty(value = "보고서 날짜")
	private String report_date;
	@ApiModelProperty(value = "어르신 ID")
	private int user_id;
	@ApiModelProperty(value = "체조 ID")
	private int exercise_id;
	@ApiModelProperty(value = "체조 정확도")
	private String exercise_accuracy;
	@ApiModelProperty(value = "문제 1번")
	private int question1_id;
	@ApiModelProperty(value = "문제 2번")
	private int question2_id;
	@ApiModelProperty(value = "문제 3번")
	private int question3_id;
	@ApiModelProperty(value = "문제 4번")
	private int question4_id;
	@ApiModelProperty(value = "문제 5번")
	private int question5_id;
	@ApiModelProperty(value = "맞은 문제 번호")
	private String right_numbers;
	@ApiModelProperty(value = "틀린 문제 번호")
	private String wrong_numbers;

}
