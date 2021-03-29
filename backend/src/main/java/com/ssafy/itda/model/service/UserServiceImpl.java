package com.ssafy.itda.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.model.User;
import com.ssafy.itda.model.mapper.ExerciseMapper;
import com.ssafy.itda.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public User getUser(int userId) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getUser(userId);
	}

}
