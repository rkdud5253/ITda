package com.ssafy.itda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.itda.domain.AccessCheck;
import com.ssafy.itda.service.AccessCheckService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("AccessCheckController")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/AccessCheck")
public class AccessCheckController {

    private static final Logger logger = LoggerFactory.getLogger(AccessCheckController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private AccessCheckService accessCheckService;

    @ApiOperation(value = "인증 절차", notes = "인증 절차를 밟고 있는지 확인한다.", response = AccessCheck.class)
    @GetMapping
    public ResponseEntity<AccessCheck> getAccessCheckByUserName(@RequestParam("userName") @ApiParam(value = "어르신 이름", required = true) String userName) throws Exception {
        logger.info("getAccessCheckByUserName - 호출");

        return new ResponseEntity<AccessCheck>(accessCheckService.getAccessCheckByUserName(userName), HttpStatus.OK);
    }

    @ApiOperation(value = "인증 절차", notes = "인증 절차를 밟고 있는지 확인한다.", response = AccessCheck.class)
    @GetMapping("/adminId")
    public ResponseEntity<AccessCheck> getAccessCheckByAdminId(@RequestParam("adminId") @ApiParam(value = "가족 ID", required = true) int adminId) throws Exception {
        logger.info("getAccessCheckByAdminId - 호출");

        return new ResponseEntity<AccessCheck>(accessCheckService.getAccessCheckByAdminId(adminId), HttpStatus.OK);
    }

    @ApiOperation(value = "인증 절차 등록", notes = "인증 절차 등록하기", response = AccessCheck.class)
    @PostMapping
    public ResponseEntity<String> postAccessCheck(@RequestBody @ApiParam(value = "가족 ID", required = true) AccessCheck accessCheck) throws Exception {
        logger.info("postAccessCheck - 호출");

        if(accessCheckService.postAccessCheck(accessCheck)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "인증 절차 해제", notes = "인증 절차 해제하기", response = AccessCheck.class)
    @DeleteMapping
    public ResponseEntity<String> deleteAccessCheck(@RequestParam("adminId") @ApiParam(value = "가족 ID", required = true) int adminId) throws Exception {
        logger.info("deleteAccessCheck - 호출");

        if(accessCheckService.deleteAccessCheck(adminId)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }
}
