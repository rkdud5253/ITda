package com.ssafy.itda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.itda.domain.Order;
import com.ssafy.itda.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("OrderController")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "명령어", notes = "명령어가 등록되어 있는지 확인한다.", response = Order.class)
    @GetMapping
    public ResponseEntity<Order> getOrder(@RequestParam("hashIp") @ApiParam(value = "Hash IP", required = true) String hashIp) throws Exception {
        logger.info("getOrder - 호출");

        return new ResponseEntity<Order>(orderService.getOrder(hashIp), HttpStatus.OK);
    }

    @ApiOperation(value = "명령어 등록", notes = "명령어 등록하기", response = Order.class)
    @PostMapping
    public ResponseEntity<String> postOrder(@RequestBody @ApiParam(value = "명령 정보", required = true) Order order) throws Exception {
        logger.info("postOrder - 호출");

        if(orderService.postOrder(order)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "명령어 해제", notes = "명령어 해제하기", response = Order.class)
    @DeleteMapping
    public ResponseEntity<String> deleteOrder(@RequestParam("hashIp") @ApiParam(value = "Hash IP", required = true) String hashIp) throws Exception {
        logger.info("deleteOrder - 호출");

        if(orderService.deleteOrder(hashIp)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }
}
