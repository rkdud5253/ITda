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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("UserAdminController")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/useradmin")
public class UserAdminController {
    private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private UserAdminService userAdminService;

    @ApiOperation(value = "가족에 등록되어 있는 어르신 ID", notes = "가족에 등록되어 있는 어르신 ID를 반환한다.", response = UserAdmin.class)
    @GetMapping("/admin")
    public ResponseEntity<List<UserAdmin>> getUserByAdminId(@RequestParam("adminId") @ApiParam(value = "가족 ID", required = true) int adminId) throws Exception {
        logger.info("getUserByAdminId - 호출");

        if(userAdminService.getUserByAdminId(adminId) == null) {
            throw new NullPointerException();
        }
        return new ResponseEntity<List<UserAdmin>>(userAdminService.getUserByAdminId(adminId), HttpStatus.OK);
    }

    @ApiOperation(value = "어르신에 등록되어 있는 가족 ID", notes = "어르신에 등록되어 있는 가족 ID를 반환한다.", response = UserAdmin.class)
    @GetMapping("/user")
    public ResponseEntity<List<UserAdmin>> getAdminByUserId(@RequestParam("userId") @ApiParam(value = "가족 ID", required = true) int userId) throws Exception {
        logger.info("getAdminByUserId - 호출");

        if(userAdminService.getAdminByUserId(userId) == null) {
            throw new NullPointerException();
        }
        return new ResponseEntity<List<UserAdmin>>(userAdminService.getAdminByUserId(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "가족, 어르신 정보", notes = "가족, 어르신 관계를 등록한다.", response = UserAdmin.class)
    @PostMapping
    public ResponseEntity<String> postUserAdmin(@RequestBody @ApiParam(value = "가족, 어르신 ID", required = true) UserAdmin userAdmin) throws Exception {
        logger.info("postUserAdmin - 호출");

        if(userAdminService.postUserAdmin(userAdmin)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
}
