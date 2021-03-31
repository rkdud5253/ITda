package com.ssafy.itda.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/file")
public class FileStorageController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileStorageController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@ApiOperation(value = "사진 저장", notes = "사진 파일 정보를 저장한다.", response = FileStorage.class)
	@PutMapping("/photo")
	public ResponseEntity<String> savePhoto(@RequestBody @ApiParam(value = "사진 파일 정보", required = true) FileStorage file) throws Exception {
		logger.info("savePhoto - 호출");
		if(fileStorageService.savePhoto(file)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "동영상 저장", notes = "동영상 파일 정보를 저장한다.", response = FileStorage.class)
	@PutMapping("/video")
	public ResponseEntity<String> saveVideo(@RequestBody @ApiParam(value = "동영상 파일 정보", required = true) FileStorage file) throws Exception {
		logger.info("saveVideo - 호출");
		if(fileStorageService.saveVideo(file)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "사진, 동영상 보기", notes = "오늘의 사진, 동영상 파일 정보를 반환한다.", response = FileStorage.class)
	@GetMapping
	public ResponseEntity<FileStorage> getFile(@RequestParam("userId") @ApiParam(value = "어르신 ID.", required = true) int userId,
			@RequestParam("curDay") @ApiParam(value = "날짜", required = true) String curDay) throws Exception {
		logger.info("getFile - 호출");
	    Map<String, Object> map = new HashMap<>();
	    map.put("userId", userId);
	    map.put("curDay", curDay);
	    return new ResponseEntity<FileStorage>(fileStorageService.getFile(map), HttpStatus.OK);
	}
	
}
