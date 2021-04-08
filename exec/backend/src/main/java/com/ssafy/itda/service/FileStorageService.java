package com.ssafy.itda.service;

import java.util.HashMap;
import java.util.List;
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
	
	public boolean saveSampleFile(FileStorage file) throws Exception {
		return sqlSession.getMapper(FileStorageMapper.class).saveSampleFile(file) == 1;
	}

	public List<FileStorage> getSampleFile(Map<String, Object> map) throws Exception {
		FileStorage file = new FileStorage();
		file.setFileDate((String) map.get("fileDate"));
		file.setFileUse((String) map.get("use"));
		return sqlSession.getMapper(FileStorageMapper.class).getSampleFile(file);
	}

	public List<FileStorage> getFile(Map<String, Object> map) throws Exception {
		FileStorage file = new FileStorage();
		file.setFileDate((String) map.get("fileDate"));
		file.setUserId((int) map.get("userId"));
		file.setFileUse((String) map.get("use"));
		return sqlSession.getMapper(FileStorageMapper.class).getFile(file);
	}

	public int getFileCnt(Map<String, Object> map) throws Exception {
		FileStorage file = new FileStorage();
		file.setFileDate((String) map.get("fileDate"));
		file.setUserId((int) map.get("userId"));
		file.setFileUse((String) map.get("use"));
		return sqlSession.getMapper(FileStorageMapper.class).getFileCnt(file);
	}

}
