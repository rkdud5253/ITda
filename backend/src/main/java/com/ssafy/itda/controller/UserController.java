package com.ssafy.itda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.itda.domain.User;
import com.ssafy.itda.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	public ResponseEntity<User> getUser(@RequestParam("user_id") @ApiParam(value = "어르신 ID.", required = true) int user_id) throws Exception {
	    logger.info("getUser - 호출");
	    return new ResponseEntity<User>(userService.getUser(user_id), HttpStatus.OK);
	}
	
}
