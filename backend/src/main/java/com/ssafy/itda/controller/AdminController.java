package com.ssafy.itda.controller;

import com.ssafy.itda.domain.Admin;
import com.ssafy.itda.domain.Question;
import com.ssafy.itda.service.AdminService;
import com.ssafy.itda.util.Sha256;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("AdminController")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "가족 정보", notes = "가족 정보를 반환한다.", response = Admin.class)
    @GetMapping("/{adminId}")
    public ResponseEntity<Admin> getAdmin(@RequestParam("adminId") @ApiParam(value = "가족 ID", required = true) int adminId) throws Exception {
        logger.info("getAdmin - 호출");

        if(adminService.getAdmin(adminId) == null) {
            throw new NullPointerException();
        }
        return new ResponseEntity<Admin>(adminService.getAdmin(adminId), HttpStatus.OK);
    }

    @ApiOperation(value = "회원가입", notes = "회원가입하기", response = Admin.class)
    @PostMapping("/signup")
    public ResponseEntity<String> adminSignUp(@RequestBody @ApiParam(value = "가족 정보", required = true) Admin admin) throws Exception {
        logger.info("adminSignUp - 호출");

        String encryptPassword = Sha256.encrypt(admin.getAdminPwd());
        admin.setAdminPwd(encryptPassword);

        if(adminService.signUp(admin)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "로그인", notes = "로그인하기", response = Admin.class)
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody @ApiParam(value = "가족 정보", required = true) Admin admin) throws Exception {
        logger.info("adminLogin - 호출");
        String encryptPassword = Sha256.encrypt(admin.getAdminPwd());
        admin.setAdminPwd(encryptPassword);

        if(adminService.login(admin)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }
}
