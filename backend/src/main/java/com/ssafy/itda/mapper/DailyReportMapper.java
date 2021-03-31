package com.ssafy.itda.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.itda.domain.DailyReport;

@Mapper
public interface DailyReportMapper {
	
	public List<DailyReport> getReportList(Map<String, Object> map) throws SQLException;
	
	public DailyReport getReport(Map<String, Object> map) throws SQLException;
	
	public DailyReport getExercise(Map<String, Object> map) throws SQLException;
	
	public DailyReport getQuestion(Map<String, Object> map) throws SQLException;
	
}
