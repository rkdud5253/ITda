package com.ssafy.itda.service;

import com.ssafy.itda.domain.Question;
import com.ssafy.itda.domain.WrongQuestion;
import com.ssafy.itda.mapper.AccessCheckMapper;
import com.ssafy.itda.mapper.QuestionMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class QuestionService {

	private final SqlSession sqlSession;

	@Autowired
	public QuestionService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Question getQuestion(int questionId) throws Exception {
		return sqlSession.getMapper(QuestionMapper.class).getQuestion(questionId);
	}

	public List<Question> getQuestionList(int userId) throws Exception {
		return sqlSession.getMapper(QuestionMapper.class).getQuestionList(userId);
	}

	public List<Question> getQuestionListByAdminId(int adminId) throws Exception {
		return sqlSession.getMapper(QuestionMapper.class).getQuestionListByAdminId(adminId);
	}

	public List<Question> getQuestionResult(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(QuestionMapper.class).getQuestionResult(map);
	}

	public List<Question> setQuestionList(int userId) throws Exception {
		// wrongQuestion 어르신 Id 기준으로 탐색 -> 2개까지만
		// 틀린 문제 2개 >> 가족이 낸 문제 2개까지 >> 나머지는 랜덤 문제

		int questionCnt = 5;
		List<Integer> questionList = new ArrayList<>();
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());

		WrongQuestionService wrongQuestionService = new WrongQuestionService(sqlSession);

		List<WrongQuestion> wrongQuestionList = wrongQuestionService.getWrongQuestionList(userId);

		int size = Math.min(wrongQuestionList.size(), 2);

		// 틀린 문제들 받은 뒤 최대 2개까지만
		while (size > 0) {
			int index = random.nextInt(wrongQuestionList.size());
			if (size > 1) {
				while (questionList.contains(wrongQuestionList.get(index).getQuestionId())) {
					random.setSeed(System.currentTimeMillis());
					index = random.nextInt(wrongQuestionList.size());
				}
			}
			if (getQuestion(wrongQuestionList.get(index).getQuestionId()) == null) {
				throw new NullPointerException();
			}

			if (questionList.contains(wrongQuestionList.get(index).getQuestionId()))
				break;

			questionList.add(wrongQuestionList.get(index).getQuestionId());
			questionCnt--;
			size--;
		}

		List<Question> questionListByUserId = getQuestionList(userId);
		size = Math.min(questionListByUserId.size(), 2);

		random.setSeed(System.currentTimeMillis());

		// 가족들이 내준 문제 2개까지 풀기
		while (size > 0) {
			int index = random.nextInt(questionListByUserId.size());
			if (size > 1) {
				while (questionList.contains(questionListByUserId.get(index).getQuestionId())) {
					random.setSeed(System.currentTimeMillis());
					index = random.nextInt(questionListByUserId.size());
				}
			}
			if (getQuestion(questionListByUserId.get(index).getQuestionId()) == null) {
				throw new NullPointerException();
			}

			if (questionList.contains(questionListByUserId.get(index).getQuestionId()))
				break;

			questionList.add(questionListByUserId.get(index).getQuestionId());
			questionCnt--;
			size--;
		}

		List<Question> responseQuestionList = new ArrayList<>(); // response 문제 리스트
		size = questionGetSize();

		random.setSeed(System.currentTimeMillis());

		// 틀린 문제 끗끗
		while (questionCnt > 0) {
			int index = random.nextInt(size) + 1;
			while (questionList.contains(index)) {
				random.setSeed(System.currentTimeMillis());
				index = random.nextInt(size) + 1;
			}
			questionList.add(index);
			questionCnt--;
		}

		for (int i = 0; i < questionList.size(); i++)
			responseQuestionList.add(getQuestion(questionList.get(i)));

		return responseQuestionList;
	}

	public int questionGetSize() throws Exception {
		return sqlSession.getMapper(QuestionMapper.class).questionGetSize();
	}

	public boolean postQuestion(Question question) throws Exception {
		return sqlSession.getMapper(QuestionMapper.class).postQuestion(question) == 1;
	}

	public boolean updateQuestion(Question question) throws Exception {
		return sqlSession.getMapper(QuestionMapper.class).updateQuestion(question) == 1;
	}

	public boolean deleteQuestion(int questionId) throws Exception{
		return sqlSession.getMapper(QuestionMapper.class).deleteQuestion(questionId) == 1;
	}
}
