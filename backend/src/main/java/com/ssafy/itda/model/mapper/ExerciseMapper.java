package com.ssafy.itda.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.itda.model.Exercise;
import com.ssafy.itda.model.DailyReport;

@Mapper
public interface ExerciseMapper {
	
	public Exercise getExercise(int exerciseId) throws SQLException;

	public int updateAccuracy(DailyReport report) throws SQLException;
	
}
