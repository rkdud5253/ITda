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
	
	public String getReport_date() {
		return report_date;
	}
	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getExercise_id() {
		return exercise_id;
	}
	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}
	public String getExercise_accuracy() {
		return exercise_accuracy;
	}
	public void setExercise_accuracy(String exercise_accuracy) {
		this.exercise_accuracy = exercise_accuracy;
	}
	public int getQuestion1_id() {
		return question1_id;
	}
	public void setQuestion1_id(int question1_id) {
		this.question1_id = question1_id;
	}
	public int getQuestion2_id() {
		return question2_id;
	}
	public void setQuestion2_id(int question2_id) {
		this.question2_id = question2_id;
	}
	public int getQuestion3_id() {
		return question3_id;
	}
	public void setQuestion3_id(int question3_id) {
		this.question3_id = question3_id;
	}
	public int getQuestion4_id() {
		return question4_id;
	}
	public void setQuestion4_id(int question4_id) {
		this.question4_id = question4_id;
	}
	public int getQuestion5_id() {
		return question5_id;
	}
	public void setQuestion5_id(int question5_id) {
		this.question5_id = question5_id;
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
