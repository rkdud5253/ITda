package com.ssafy.itda.controller;

import com.ssafy.itda.domain.WrongQuestion;
import com.ssafy.itda.service.WrongQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("WrongQuestionController")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/wrong")
public class WrongQuestionController {
    private static final Logger logger = LoggerFactory.getLogger(WrongQuestionController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private WrongQuestionService WrongQuestionService;

    // @ApiOperation(value = "문제 리스트 반환", notes = "문제 5개를 반환", response =
    // WrongQuestion.class)
    @GetMapping
    public ResponseEntity<WrongQuestion> getWrongQuestion(
            @RequestParam("userId") @ApiParam(value = "유저 ID", required = true) int userId,
            @RequestParam("questionId") @ApiParam(value = "문제 ID", required = true) int questionId) throws Exception {
        logger.info("getWrongQuestion - 호출");

        return new ResponseEntity<>(WrongQuestionService.getWrongQuestion(userId,questionId), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<WrongQuestion>> getWrongQuestionList(
            @RequestParam("userId") @ApiParam(value = "어르신 ID", required = true) int userId) throws Exception {
        logger.info("getWrongQuestionList - 호출");

        return new ResponseEntity<>(WrongQuestionService.getWrongQuestionList(userId), HttpStatus.OK);
    }

    // @ApiOperation(value = "문제 제출", notes = "문제 DB에 등록", response =
    // WrongQuestion.class)
    @PostMapping
    public ResponseEntity<String> postWrongQuestion(@RequestBody WrongQuestion wrongQuestion) throws Exception {
        logger.info("postWrongQuestion - 호출");

        if (WrongQuestionService.postWrongQuestion(wrongQuestion)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }

        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    // @ApiOperation(value = "문제 삭제", notes = "문제 DB에 삭제", response =
    // WrongQuestion.class)
    @DeleteMapping
    public ResponseEntity<String> deleteWrongQuestion(@RequestBody WrongQuestion wrongQuestion) throws Exception {
        logger.info("deleteWrongQuestion - 호출");

        if (WrongQuestionService.deleteWrongQuestion(wrongQuestion)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }

        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }
}
