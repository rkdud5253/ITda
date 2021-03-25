package com.ssafy.itda.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.model.Exercise;
import com.ssafy.itda.model.Report;
import com.ssafy.itda.model.mapper.ExerciseMapper;

@Service
public class ExerciseServiceImpl implements ExerciseService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Exercise getExercise(String exerciseId) throws Exception {
		return sqlSession.getMapper(ExerciseMapper.class).getExercise(exerciseId);
	}

	@Override
	public boolean updateAccuracy(Report report) throws Exception {
		return sqlSession.getMapper(ExerciseMapper.class).updateAccuracy(report) == 1;
	}

}
