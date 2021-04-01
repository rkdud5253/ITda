package com.ssafy.itda.domain;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "FileStorage : 파일 정보")
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
	@NotNull
	@ApiModelProperty(value = "파일 형식")
	private String fileType;
	@NotNull
	@ApiModelProperty(value = "파일 이름")
	private String fileName;
	@NotNull 
	@ApiModelProperty(value = "파일 URL")
	private String fileUrl;
	@NotNull 
	@ApiModelProperty(value = "파일 사용처")
	private String fileUse;
	@ApiModelProperty(value = "감정 인식")
	private String imageEmotion;
	
}
