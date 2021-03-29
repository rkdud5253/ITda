package com.ssafy.itda.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("QuestionController")
@RestController
@RequestMapping("/question")
public class QuestionController {
  private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);
  private static final String SUCCESS = "success";
  private static final String FAIL = "fail";

}
