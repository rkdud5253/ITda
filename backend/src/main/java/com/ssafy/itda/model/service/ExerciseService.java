package com.ssafy.itda.model.service;

import com.ssafy.itda.model.Exercise;
import com.ssafy.itda.model.Report;

public interface ExerciseService {

	public Exercise getExercise(String exerciseId) throws Exception;

	public boolean updateAccuracy(Report report) throws Exception;

}
