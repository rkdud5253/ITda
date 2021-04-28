package com.ssafy.itda.service;

import com.ssafy.itda.domain.Order;
import com.ssafy.itda.mapper.OrderMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final SqlSession sqlSession;

    @Autowired
    public OrderService(SqlSession sqlSession) { this.sqlSession = sqlSession;	}

    public Order getOrder(String hashIp) throws Exception{
        return sqlSession.getMapper(OrderMapper.class).getOrder(hashIp);
    }

    public boolean postOrder(Order order) throws Exception{
        return sqlSession.getMapper(OrderMapper.class).postOrder(order) == 1;
    }

    public boolean deleteOrder(String hashIp) throws Exception{
        return sqlSession.getMapper(OrderMapper.class).deleteOrder(hashIp) == 1;
    }
}
