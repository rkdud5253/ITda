package com.ssafy.itda.controller;

import com.ssafy.itda.domain.UserAdmin;
import com.ssafy.itda.service.UserAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("UserAdminController")
@RestController
@RequestMapping("/useradmin")
public class UserAdminController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private UserAdminService userAdminService;

    @ApiOperation(value = "가족, 어르신 정보", notes = "가족, 어르신 정보를 반환한다.", response = UserAdmin.class)
    @GetMapping
    public ResponseEntity<UserAdmin> getUserAdmin(@RequestParam("userAdminId") @ApiParam(value = "가족, 어르신 ID.", required = true) int userAdminId) throws Exception {
        logger.info("getUserAdmin - 호출");
        return new ResponseEntity<UserAdmin>(userAdminService.getUserAdmin(userAdminId), HttpStatus.OK);
    }
}
