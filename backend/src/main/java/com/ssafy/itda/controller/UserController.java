package com.ssafy.itda.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.itda.domain.User;
import com.ssafy.itda.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("UserController")
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;

	@ApiOperation(value = "어르신 정보", notes = "어르신 정보를 반환한다.", response = User.class)
	@GetMapping
	public ResponseEntity<User> getUser(@RequestParam("userId") @ApiParam(value = "어르신 ID", required = true) int userId) throws Exception {
		logger.info("getUser - 호출");
		return new ResponseEntity<User>(userService.getUser(userId), HttpStatus.OK);
	}

	@ApiOperation(value = "어르신 정보", notes = "어르신 정보를 등록한다.", response = User.class)
	@PostMapping
	public ResponseEntity<String> postUser(@RequestBody @ApiParam(value = "어르신 정보", required = true) User user) throws Exception {
		logger.info("getUser - 호출");

		if(userService.postUser(user)){
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<>(FAIL, HttpStatus.OK);
	}
}
