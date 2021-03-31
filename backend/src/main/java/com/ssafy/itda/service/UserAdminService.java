package com.ssafy.itda.service;

import com.ssafy.itda.domain.UserAdmin;
import com.ssafy.itda.mapper.UserAdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserAdminService {
    private final SqlSession sqlSession;

    @Autowired
    public UserAdminService(SqlSession sqlSession) { this.sqlSession = sqlSession;	}

    public List<UserAdmin> getUserByAdminId(int adminId) throws SQLException{
        return sqlSession.getMapper(UserAdminMapper.class).getUserByAdminId(adminId);
    }

    public List<UserAdmin> getAdminByUserId(int userId) throws SQLException{
        return sqlSession.getMapper(UserAdminMapper.class).getAdminByUserId(userId);
    }

    public boolean postUserAdmin(UserAdmin userAdmin) throws SQLException{
        return sqlSession.getMapper(UserAdminMapper.class).postUserAdmin(userAdmin) == 1;
    }

}
