package com.ssafy.itda.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.domain.FileStorage;
import com.ssafy.itda.mapper.FileStorageMapper;

@Service
public class FileStorageService {

	private final SqlSession sqlSession;

	@Autowired
	public FileStorageService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public boolean saveFile(FileStorage file) throws Exception {
		return sqlSession.getMapper(FileStorageMapper.class).saveFile(file) == 1;
	}
	
	public FileStorage getFile(FileStorage file) throws Exception {
		return sqlSession.getMapper(FileStorageMapper.class).getFile(file);
	}
	
	public int getFileCnt(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(FileStorageMapper.class).getFileCnt(map);
	}
	
}
