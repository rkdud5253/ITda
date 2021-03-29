package com.ssafy.itda.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Exercise : 체조 영상 정보")
public class Exercise {

	@ApiModelProperty(value = "체조 ID")
	private String exerciseId;
	@ApiModelProperty(value = "체조 영상 이름")
	private String exerciseName;
	@ApiModelProperty(value = "체조 영상 url")
	private String exerciseUrl;

	public String getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(String exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getExerciseUrl() {
		return exerciseUrl;
	}

	public void setExerciseUrl(String exerciseUrl) {
		this.exerciseUrl = exerciseUrl;
	}
	
}
