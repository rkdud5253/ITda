package com.ssafy.itda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.itda.model.Exercise;
import com.ssafy.itda.model.Report;
import com.ssafy.itda.model.service.ExerciseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("ExerciseController")
@RestController
@RequestMapping("/exercise")
public class ExerciseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ExerciseService exerciseService;

	@ApiOperation(value = "체조 영상보기", notes = "오늘의 체조 영상 정보를 반환한다.", response = Exercise.class)
	@GetMapping
	public ResponseEntity<Exercise> getExercise(@RequestParam("exerciseId") @ApiParam(value = "오늘의 체조 ID.", required = true) String exerciseId) throws Exception {
	    logger.info("getExercise - 호출");
	    return new ResponseEntity<Exercise>(exerciseService.getExercise(exerciseId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "체조 영상 정확도", notes = "오늘의 체조 영상 정확도를 반환한다.", response = Exercise.class)
	@PutMapping
	public ResponseEntity<String> updateAccuracy(@RequestBody @ApiParam(value = "데일리 보고서 정보", required = true) Report report) throws Exception {
	    logger.info("updateAccuracy - 호출");
	    if(exerciseService.updateAccuracy(report)) {
	    	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	    }
	    return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}
