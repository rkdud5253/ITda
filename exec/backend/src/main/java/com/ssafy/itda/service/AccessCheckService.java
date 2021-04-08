package com.ssafy.itda.service;

import com.ssafy.itda.domain.AccessCheck;
import com.ssafy.itda.mapper.AccessCheckMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessCheckService {
    private final SqlSession sqlSession;

    @Autowired
    public AccessCheckService(SqlSession sqlSession) { this.sqlSession = sqlSession;	}

    public AccessCheck getAccessCheckByUserName(String userName) throws Exception{
        return sqlSession.getMapper(AccessCheckMapper.class).getAccessCheckByUserName(userName);
    }

    public AccessCheck getAccessCheckByAdminId(int adminId) throws Exception{
        return sqlSession.getMapper(AccessCheckMapper.class).getAccessCheckByAdminId(adminId);
    }

    public boolean postAccessCheck(AccessCheck accessCheck) throws Exception{
        return sqlSession.getMapper(AccessCheckMapper.class).postAccessCheck(accessCheck) == 1;
    }

    public boolean deleteAccessCheck(int adminId) throws Exception{
        return sqlSession.getMapper(AccessCheckMapper.class).deleteAccessCheck(adminId) == 1;
    }
}
