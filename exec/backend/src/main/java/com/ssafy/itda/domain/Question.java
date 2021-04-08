package com.ssafy.itda.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Question : 문제 정보")
public class Question {
  @NotNull
  @ApiModelProperty(value = "문제 ID")
  private int questionId;
  @ApiModelProperty(value = "어르신 ID")
  private int userId;
  @ApiModelProperty(value = "가족 ID")
  private int adminId;
  @NotNull
  @ApiModelProperty(value = "문제 내용")
  private String questionContent;
  @ApiModelProperty(value = "문제 사진")
  private String questionImageUrl;
  @NotNull
  @ApiModelProperty(value = "보기 1")
  private String example1;
  @NotNull
  @ApiModelProperty(value = "보기 2")
  private String example2;
  @ApiModelProperty(value = "보기 3")
  private String example3;
  @ApiModelProperty(value = "보기 4")
  private String example4;
  @NotNull
  @ApiModelProperty(value = "정답")
  private int answer;
}
