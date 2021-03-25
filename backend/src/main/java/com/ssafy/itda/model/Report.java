package com.ssafy.itda.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Report : 데일리 보고서 정보")
public class Report {

	@ApiModelProperty(value = "보고서 날짜")
	private String reportDate;
	@ApiModelProperty(value = "어르신 ID")
	private String userId;
	@ApiModelProperty(value = "체조 ID")
	private String exerciseId;
	@ApiModelProperty(value = "체조 정확도")
	private String exerciseAccuracy;
	@ApiModelProperty(value = "문제 1번")
	private String question1Id;
	@ApiModelProperty(value = "문제 2번")
	private String question2Id;
	@ApiModelProperty(value = "문제 3번")
	private String question3Id;
	@ApiModelProperty(value = "문제 4번")
	private String question4Id;
	@ApiModelProperty(value = "문제 5번")
	private String question5Id;
	@ApiModelProperty(value = "맞은 문제 번호")
	private String right_numbers;
	@ApiModelProperty(value = "틀린 문제 번호")
	private String wrong_numbers;
	
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(String exerciseId) {
		this.exerciseId = exerciseId;
	}
	public String getExerciseAccuracy() {
		return exerciseAccuracy;
	}
	public void setExerciseAccuracy(String exerciseAccuracy) {
		this.exerciseAccuracy = exerciseAccuracy;
	}
	public String getQuestion1Id() {
		return question1Id;
	}
	public void setQuestion1Id(String question1Id) {
		this.question1Id = question1Id;
	}
	public String getQuestion2Id() {
		return question2Id;
	}
	public void setQuestion2Id(String question2Id) {
		this.question2Id = question2Id;
	}
	public String getQuestion3Id() {
		return question3Id;
	}
	public void setQuestion3Id(String question3Id) {
		this.question3Id = question3Id;
	}
	public String getQuestion4Id() {
		return question4Id;
	}
	public void setQuestion4Id(String question4Id) {
		this.question4Id = question4Id;
	}
	public String getQuestion5Id() {
		return question5Id;
	}
	public void setQuestion5Id(String question5Id) {
		this.question5Id = question5Id;
	}
	public String getRight_numbers() {
		return right_numbers;
	}
	public void setRight_numbers(String right_numbers) {
		this.right_numbers = right_numbers;
	}
	public String getWrong_numbers() {
		return wrong_numbers;
	}
	public void setWrong_numbers(String wrong_numbers) {
		this.wrong_numbers = wrong_numbers;
	}
	
}
