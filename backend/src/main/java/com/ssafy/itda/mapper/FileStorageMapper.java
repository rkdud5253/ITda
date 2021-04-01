package com.ssafy.itda.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.itda.domain.FileStorage;

@Mapper
public interface FileStorageMapper {

	public int saveFile(FileStorage file) throws Exception;
	
	public FileStorage getFile(FileStorage file) throws SQLException;

	public int getFileCnt(Map<String, Object> map) throws SQLException;
	
}
