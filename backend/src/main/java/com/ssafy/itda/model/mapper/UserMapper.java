package com.ssafy.itda.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.itda.model.User;

@Mapper
public interface UserMapper {
	
	public User getUser(int userId) throws SQLException;
	
}
