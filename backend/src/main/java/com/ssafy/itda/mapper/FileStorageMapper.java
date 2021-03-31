package com.ssafy.itda.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.itda.domain.FileStorage;

@Mapper
public interface FileStorageMapper {

	public int savePhoto(FileStorage file) throws Exception;

	public int saveVideo(FileStorage file) throws Exception;
	
	public FileStorage getFile(Map<String, Object> map) throws SQLException;
	
	public FileStorage getPhoto(Map<String, Object> map) throws SQLException;
	
	public FileStorage getVideo(Map<String, Object> map) throws SQLException;

	public int getFileCnt(Map<String, Object> map) throws SQLException;

	public int createFileStorage(Map<String, Object> map) throws SQLException;
	
}
