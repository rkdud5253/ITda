package com.ssafy.itda.controller;

import com.ssafy.itda.domain.DailyReport;
import com.ssafy.itda.domain.Question;
import com.ssafy.itda.domain.WrongQuestion;
import com.ssafy.itda.service.QuestionService;
import com.ssafy.itda.service.WrongQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

@Api("QuestionController")
@RestController
@RequestMapping("/qna")
public class QuestionController {
  private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
  private static final String SUCCESS = "success";
  private static final String FAIL = "fail";

  @Autowired
  private QuestionService questionService;

  @Autowired
  private WrongQuestionService wrongQuestionService;

  // @ApiOperation(value = "문제 리스트 반환", notes = "문제 5개를 반환", response = Question.class)
  @GetMapping("/{userId}")
  public ResponseEntity<List<Question>> getQuestion(@RequestParam("userId") @ApiParam(value = "유저 ID", required = true) int userId) throws Exception {
    logger.info("getQuestion - 호출");

    // wrongQuestion 어르신 Id 기준으로 탐색 -> 2개까지만
    // 틀린 문제 2개 >> 가족이 낸 문제 2개까지 >> 나머지는 랜덤 문제

    int questionCnt = 5;
    List<Integer> questionList = new ArrayList<>();
    Random random = new Random();

    List<WrongQuestion> wrongQuestionList = wrongQuestionService.getWrongQuestionList(userId);

    int size = wrongQuestionList.size() > 2 ? 2 : wrongQuestionList.size();

    while(size-- > 0) {
      int index = random.nextInt(wrongQuestionList.size()) + 1;
      while(questionList.contains(index))
        index = random.nextInt(wrongQuestionList.size()) + 1;

      if(questionService.getQuestion(index) == null) {
        throw new NullPointerException();
      }

      questionList.add(index);
      questionCnt--;
    }

    // 틀린 문제들 받은 뒤 최대 2개까지만
    List<WrongQuestion> questionListByUserId = wrongQuestionService.getWrongQuestionList(userId);

    size = questionListByUserId.size() > 2 ? 2 : questionListByUserId.size();

    while(size-- > 0) {
      int index = random.nextInt(questionListByUserId.size()) + 1;
      while(questionList.contains(index))
        index = random.nextInt(questionListByUserId.size()) + 1;

      if(questionService.getQuestion(index) == null) {
        throw new NullPointerException();
      }

      questionList.add(index);
      questionCnt--;
    }

    List<Question> responseQuestionList = new ArrayList<>();

    size = responseQuestionList.size() > 2 ? responseQuestionList.size() : responseQuestionList.size() - 3;

    // 틀린 문제 끗끗
    while(questionCnt-1 >= 0) {
      int index = random.nextInt(responseQuestionList.size()) + 1;
      while(questionList.contains(index))
        index = random.nextInt(responseQuestionList.size()) + 1;

      for(int i = 0; i < questionList.size(); i++)
        responseQuestionList.add(questionService.getQuestion(questionList.get(i)));

      questionList.add(index);
      questionCnt--;
    }

    for(int i = 0; i < questionList.size(); i++) {
      responseQuestionList.add(questionService.getQuestion(i));
    }

    return new ResponseEntity<>(responseQuestionList, HttpStatus.OK);
  }

  // @ApiOperation(value = "문제 1개 반환", notes = "id가 맞는 문제 1개 반환", response = Question.class)
  @GetMapping("/{questionId}")
  public ResponseEntity<Question> getQuestion(@PathVariable Integer questionId) throws Exception {
    logger.info("getQuestion - 호출");

    return new ResponseEntity<>(questionService.getQuestion(questionId), HttpStatus.OK);
  }

  // @ApiOperation(value = "문제 제출", notes = "문제 DB에 등록", response = Question.class)
  @PostMapping
  public ResponseEntity<String> postQuestion(@RequestBody Question question) throws Exception {
    logger.info("postQuestion - 호출");

    if(questionService.postQuestion(question)) {
      return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    return new ResponseEntity<>(FAIL, HttpStatus.OK);
  }

  // @ApiOperation(value = "문제 제출", notes = "문제 DB에 등록", response = Question.class)
  @PutMapping
  public ResponseEntity<String> updateQuestion(@RequestBody Question question) throws Exception {
    logger.info("updateQuestion - 호출");

    if(questionService.updateQuestion(question)) {
      return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    return new ResponseEntity<>(FAIL, HttpStatus.OK);
  }
}
