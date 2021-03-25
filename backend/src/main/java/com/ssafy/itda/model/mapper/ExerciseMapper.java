package com.ssafy.itda.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.itda.model.Exercise;
import com.ssafy.itda.model.Report;

@Mapper
public interface ExerciseMapper {
	
	public Exercise getExercise(String exerciseId) throws SQLException;

	public int updateAccuracy(Report report) throws SQLException;
	
}
