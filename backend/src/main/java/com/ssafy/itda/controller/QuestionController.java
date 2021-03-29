package com.ssafy.itda.controller;

import com.ssafy.itda.domain.DailyReport;
import com.ssafy.itda.domain.Question;
import com.ssafy.itda.domain.WrongQuestion;
import com.ssafy.itda.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("QuestionController")
@RestController
@RequestMapping("/qna")
public class QuestionController {
  private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
  private static final String SUCCESS = "success";
  private static final String FAIL = "fail";

  @Autowired
  private QuestionService questionService;

  // @ApiOperation(value = "문제 리스트 반환", notes = "문제 5개를 반환", response = Question.class)
  @GetMapping
  public ResponseEntity<Question> getQuestion() throws Exception {
    logger.info("getQuestion - 호출");

    // wrongQuestion 어르신 Id 기준으로 탐색 -> 2개까지만
    // 틀린 문제 2개 >> 가족이 낸 문제 2개까지 >> 나머지는 랜덤 문제

    WrongQuestion w = new WrongQuestion();

    return new ResponseEntity<Question>(questionService.getQuestion(), HttpStatus.OK);
  }

  // @ApiOperation(value = "문제 1개 반환", notes = "id가 맞는 문제 1개 반환", response = Question.class)
  @GetMapping("/{question_id}")
  public ResponseEntity<Question> getQuestion(@PathVariable Integer questionId) throws Exception {
    logger.info("getQuestion - 호출");

    return new ResponseEntity<Question>(questionService.getQuestion(questionId), HttpStatus.OK);
  }

  // @ApiOperation(value = "문제 제출", notes = "문제 DB에 등록", response = Question.class)
  @PostMapping
  public ResponseEntity<String> postQuestion(Question question) throws Exception {
    logger.info("postQuestion - 호출");


    return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

  }
}
