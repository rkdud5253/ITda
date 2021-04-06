package com.ssafy.itda.service;

import com.ssafy.itda.domain.User;
import com.ssafy.itda.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

	private final SqlSession sqlSession;

	@Autowired
	public UserService(SqlSession sqlSession) { this.sqlSession = sqlSession; }

	public User getUser(int userId) throws Exception{

		return sqlSession.getMapper(UserMapper.class).getUser(userId);
	}

	public int getUserCount() throws Exception{

		return sqlSession.getMapper(UserMapper.class).getUserCount();
	}

	public boolean postUser(User user) throws Exception{

		return sqlSession.getMapper(UserMapper.class).postUser(user) == 1;
	}
}
