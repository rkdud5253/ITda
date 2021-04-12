package com.ssafy.itda.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.itda.domain.FileStorage;

@Mapper
public interface FileStorageMapper {

	public int saveFile(FileStorage file) throws Exception;
	
	public int saveSampleFile(FileStorage file) throws Exception;
	
	public FileStorage getFile(FileStorage file) throws SQLException;
	
	public List<FileStorage> getSampleFile(FileStorage file) throws SQLException;
	
	public int getFileCnt(FileStorage file) throws SQLException;
	
	public int createFileInfo(Map<String, Object> map) throws SQLException;

}
