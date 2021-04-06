package com.ssafy.itda.service;

import com.ssafy.itda.domain.Admin;
import com.ssafy.itda.mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final SqlSession sqlSession;

    @Autowired
    public AdminService(SqlSession sqlSession) { this.sqlSession = sqlSession;	}

    public Admin getAdmin(int adminId) throws Exception{
        return sqlSession.getMapper(AdminMapper.class).getAdmin(adminId);
    }

    public Admin getAdminByEmail(String adminEmail) throws Exception{
        return sqlSession.getMapper(AdminMapper.class).getAdminByEmail(adminEmail);
    }

    public boolean signUp(Admin admin) throws Exception{
        return sqlSession.getMapper(AdminMapper.class).signUp(admin) == 1;
    }

    public int login(Admin admin) throws Exception{
        return sqlSession.getMapper(AdminMapper.class).login(admin);
    }
}
