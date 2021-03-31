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
	public UserService(SqlSession sqlSession) { this.sqlSession = sqlSession;	}

	public User getUser(int userId) throws Exception{

		return sqlSession.getMapper(UserMapper.class).getUser(userId);
	}

	public String makeRandomString() {
		int leftLimit = 48; // 0
		int rightLimit = 122; // z
		int targetStringLength = 15;

		return new Random().ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
	}
}
