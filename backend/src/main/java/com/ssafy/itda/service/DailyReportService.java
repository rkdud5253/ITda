package com.ssafy.itda.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.domain.DailyReport;
import com.ssafy.itda.mapper.DailyReportMapper;

@Service
public class DailyReportService {

	private final SqlSession sqlSession;

	@Autowired
	public DailyReportService(SqlSession sqlSession) {this.sqlSession = sqlSession;}

	public List<DailyReport> getReportList(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(DailyReportMapper.class).getReportList(map);
	}

	public boolean updateAccuracy(DailyReport report) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", report.getUserId());
		map.put("reportDate", report.getReportDate());
		if(getReportCnt(map) == 0) {
			createDailyReport(map);
		}
		return sqlSession.getMapper(DailyReportMapper.class).updateAccuracy(report) == 1;
	}
	
	public DailyReport getReport(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(DailyReportMapper.class).getReport(map);
	}
	
	public DailyReport getExercise(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(DailyReportMapper.class).getExercise(map);
	}

	public boolean createDailyReport(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(DailyReportMapper.class).createDailyReport(map) == 1;
	}
	
	public int getReportCnt(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(DailyReportMapper.class).getReportCnt(map);
	}
}
