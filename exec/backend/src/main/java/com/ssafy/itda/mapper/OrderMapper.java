package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface OrderMapper {
    public Order getOrder(String hashIp) throws SQLException;

    public int postOrder(Order order) throws SQLException;
    public int deleteOrder (String hashIp) throws SQLException;
}