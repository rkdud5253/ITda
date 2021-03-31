package com.ssafy.itda.service;

import com.ssafy.itda.domain.UserAdmin;
import com.ssafy.itda.mapper.UserAdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdminService {
    private final SqlSession sqlSession;

    @Autowired
    public UserAdminService(SqlSession sqlSession) { this.sqlSession = sqlSession;	}

    public UserAdmin getUserAdmin(int userAdminId) throws Exception{

        return sqlSession.getMapper(UserAdminMapper.class).getUserAdmin(userAdminId);
    }
}
