package com.ssafy.itda.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.itda.domain.FileStorage;
import com.ssafy.itda.service.FileStorageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("FileStorageController")
@RestController
@RequestMapping("/files")
public class FileStorageController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileStorageController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String IMAGE = "Image";
	private static final String VIDEO = "Video";
	private static final String DIARY = "Diary";
	private static final String EXERCISE = "Exercise";
	
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@ApiOperation(value = "일기 사진파일 저장", notes = "일기 사진파일 정보를 저장한다.", response = FileStorage.class)
	@PostMapping("/diary/Image")
	public ResponseEntity<String> saveDiaryImage(@RequestBody @ApiParam(value = "일기 사진파일 정보", required = true) FileStorage file) throws Exception {
		logger.info("saveDiaryImage - 호출");
		file.setFileType(IMAGE);
		file.setFileUse(DIARY);
		if(fileStorageService.saveFile(file)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "일기 동영상파일 저장", notes = "일기 동영상파일 정보를 저장한다.", response = FileStorage.class)
	@PostMapping("/diary/video")
	public ResponseEntity<String> saveDiaryVideo(@RequestBody @ApiParam(value = "일기 동영상파일 정보", required = true) FileStorage file) throws Exception {
		logger.info("saveDiaryVideo - 호출");
		file.setFileType(VIDEO);
		file.setFileUse(DIARY);
		if(fileStorageService.saveFile(file)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "체조 사진파일 저장", notes = "체조 사진파일 정보를 저장한다.", response = FileStorage.class)
	@PostMapping("/exercise")
	public ResponseEntity<String> saveExerciseImage(@RequestBody @ApiParam(value = "체조 사진파일 정보", required = true) FileStorage file) throws Exception {
		logger.info("saveExerciseImage - 호출");
		file.setFileType(IMAGE);
		file.setFileUse(EXERCISE);
		if(fileStorageService.saveFile(file)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "일기 사진파일 보기", notes = "오늘의 일기 사진파일 정보를 반환한다.", response = FileStorage.class)
	@GetMapping("/diary/image")
	public ResponseEntity<FileStorage> getDiaryImage(@RequestBody @ApiParam(value = "일기 사진파일 정보", required = true) FileStorage file) throws Exception {
		logger.info("getDiaryImage - 호출");
		file.setFileType(IMAGE);
		file.setFileUse(DIARY);
	    return new ResponseEntity<FileStorage>(fileStorageService.getFile(file), HttpStatus.OK);
	}

	@ApiOperation(value = "일기 동영상파일 보기", notes = "오늘의 일기 동영상파일 정보를 반환한다.", response = FileStorage.class)
	@GetMapping("/diary/video")
	public ResponseEntity<FileStorage> getDiaryVideo(@RequestBody @ApiParam(value = "일기 동영상파일 정보", required = true) FileStorage file) throws Exception {
		logger.info("getDiaryVideo - 호출");
		file.setFileType(VIDEO);
		file.setFileUse(DIARY);
	    return new ResponseEntity<FileStorage>(fileStorageService.getFile(file), HttpStatus.OK);
	}

	@ApiOperation(value = "체조 사진파일 보기", notes = "오늘의 체조 사진파일 정보를 반환한다.", response = FileStorage.class)
	@GetMapping("/exercise")
	public ResponseEntity<FileStorage> getExerciseImage(@RequestBody @ApiParam(value = "체조 사진파일 정보", required = true) FileStorage file) throws Exception {
		logger.info("getExerciseImage - 호출");
		file.setFileType(IMAGE);
		file.setFileUse(EXERCISE);
	    return new ResponseEntity<FileStorage>(fileStorageService.getFile(file), HttpStatus.OK);
	}
}
