package com.ssafy.itda.service;

import com.ssafy.itda.domain.Exercise;
import com.ssafy.itda.domain.DailyReport;
import com.ssafy.itda.mapper.ExerciseMapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

	private final SqlSession sqlSession;

	@Autowired
	public ExerciseService(SqlSession sqlSession) {this.sqlSession = sqlSession;}

	public Exercise getExercise(int exerciseId) throws Exception {
		return sqlSession.getMapper(ExerciseMapper.class).getExercise(exerciseId);
	}

	public boolean updateAccuracy(DailyReport report) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", report.getUserId());
		map.put("reportDate", report.getReportDate());
		if(getReportCnt(map) == 0) {
			createDailyReport(map);
		}
		return sqlSession.getMapper(ExerciseMapper.class).updateAccuracy(report) == 1;
	}
	
	public boolean createDailyReport(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(ExerciseMapper.class).createDailyReport(map) == 1;
	}
	
	public int getReportCnt(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(ExerciseMapper.class).getReportCnt(map);
	}
}
