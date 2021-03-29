package com.ssafy.itda.service;

import com.ssafy.itda.domain.Exercise;
import com.ssafy.itda.domain.DailyReport;
import com.ssafy.itda.mapper.ExerciseMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

	private final SqlSession sqlSession;

	@Autowired
	public ExerciseService(SqlSession sqlSession) {this.sqlSession = sqlSession;}

	public Exercise getExercise(int exercise_id) throws Exception {
		return sqlSession.getMapper(ExerciseMapper.class).getExercise(exercise_id);
	}

	public boolean updateAccuracy(DailyReport report) throws Exception {
		return sqlSession.getMapper(ExerciseMapper.class).updateAccuracy(report) == 1;
	}
}
