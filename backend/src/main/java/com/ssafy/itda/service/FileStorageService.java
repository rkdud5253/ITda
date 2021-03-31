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

	public boolean savePhoto(FileStorage file) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", file.getUserId());
		map.put("fileDate", file.getFileDate());
		if(getFileCnt(map) == 0) {
			createFileStorage(map);
		}
		return sqlSession.getMapper(FileStorageMapper.class).savePhoto(file) == 1;
	}
	
	public boolean saveVideo(FileStorage file) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", file.getUserId());
		map.put("fileDate", file.getFileDate());
		if(getFileCnt(map) == 0) {
			createFileStorage(map);
		}
		return sqlSession.getMapper(FileStorageMapper.class).saveVideo(file) == 1;
	}
	
	public FileStorage getFile(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(FileStorageMapper.class).getFile(map);
	}
	
	public FileStorage getPhoto(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(FileStorageMapper.class).getPhoto(map);
	}
	
	public FileStorage getVideo(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(FileStorageMapper.class).getVideo(map);
	}
	
	public boolean createFileStorage(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(FileStorageMapper.class).createFileStorage(map) == 1;
	}
	
	public int getFileCnt(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(FileStorageMapper.class).getFileCnt(map);
	}
	
}
