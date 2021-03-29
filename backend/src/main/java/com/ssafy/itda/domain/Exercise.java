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
	
	public int getExercise_id() {
		return exercise_id;
	}
	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}
	public String getExercise_name() {
		return exercise_name;
	}
	public void setExercise_name(String exercise_name) {
		this.exercise_name = exercise_name;
	}
	public String getExercise_url() {
		return exercise_url;
	}
	public void setExercise_url(String exercise_url) {
		this.exercise_url = exercise_url;
	}

}
