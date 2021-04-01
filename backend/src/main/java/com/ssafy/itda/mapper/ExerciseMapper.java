package com.ssafy.itda.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.itda.domain.Exercise;
import com.ssafy.itda.domain.DailyReport;

@Mapper
public interface ExerciseMapper {
	
	public Exercise getExercise(int exerciseId) throws SQLException;
	
}
