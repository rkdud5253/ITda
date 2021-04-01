package com.ssafy.itda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.domain.DailyReport;
import com.ssafy.itda.mapper.DailyReportMapper;
import com.ssafy.itda.mapper.FileStorageMapper;

@Service
public class DailyReportService {

	private final SqlSession sqlSession;

	@Autowired
	public DailyReportService(SqlSession sqlSession) {this.sqlSession = sqlSession;}

	public List<DailyReport> getReportList(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(DailyReportMapper.class).getReportList(map);
	}
	
	public DailyReport getReport(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(DailyReportMapper.class).getReport(map);
	}
	
	public DailyReport getExercise(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(DailyReportMapper.class).getExercise(map);
	}
	
	public DailyReport getQuestion(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(DailyReportMapper.class).getQuestion(map);
	}
}
