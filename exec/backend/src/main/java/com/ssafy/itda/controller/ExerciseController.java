package com.ssafy.itda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.ssafy.itda.domain.Exercise;
import com.ssafy.itda.domain.DailyReport;
import com.ssafy.itda.service.ExerciseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("ExerciseController")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/exercise")
public class ExerciseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ExerciseService exerciseService;

	@ApiOperation(value = "체조 영상보기", notes = "오늘의 체조 영상 정보를 반환한다.", response = Exercise.class)
	@GetMapping
	public ResponseEntity<Exercise> getExercise(@RequestParam("exerciseId") @ApiParam(value = "오늘의 체조 ID.", required = true) int exerciseId) throws Exception {
		logger.info("getExercise - 호출");
	    if(exerciseService.getExercise(exerciseId) == null) {
	        throw new NullPointerException();
	    }
	    return new ResponseEntity<>(exerciseService.getExercise(exerciseId), HttpStatus.OK);
	}
	
}