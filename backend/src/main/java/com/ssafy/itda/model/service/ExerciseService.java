package com.ssafy.itda.model.service;

import com.ssafy.itda.model.Exercise;
import com.ssafy.itda.model.DailyReport;

public interface ExerciseService {

	public Exercise getExercise(int exerciseId) throws Exception;

	public boolean updateAccuracy(DailyReport report) throws Exception;

}
