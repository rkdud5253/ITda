package com.ssafy.itda.domain;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "FileStorage : 사진, 동영상 파일 정보")
public class FileStorage {

	@NotNull
	@ApiModelProperty(value = "파일 ID")
	private int fileId;
	@NotNull
	@ApiModelProperty(value = "어르신 ID")
	private int userId;
	@NotNull
	@ApiModelProperty(value = "파일 날짜")
	private String fileDate;
	@ApiModelProperty(value = "사진 이름")
	private String photoName;
	@ApiModelProperty(value = "사진 URL")
	private String photoUrl;
	@ApiModelProperty(value = "동영상 이름")
	private String videoName;
	@ApiModelProperty(value = "동영상 URL")
	private String videoUrl;
	@ApiModelProperty(value = "감정 인식")
	private String imageEmotion;
	
}
