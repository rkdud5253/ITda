package com.ssafy.itda.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.itda.domain.User;

@Mapper
public interface UserMapper {
	public User getUser(int userId) throws SQLException;
}
