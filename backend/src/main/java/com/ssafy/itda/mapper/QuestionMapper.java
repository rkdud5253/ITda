package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.Question;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionMapper {

  public Question getQuestion(int questionId) throws SQLException;
  public List<Question> getQuestionList(int userId) throws SQLException;
  public List<Question> getQuestionResult(Map<String, Integer> map) throws SQLException;
  public int questionGetSize() throws SQLException;
  public int postQuestion(Question question) throws SQLException;
  public int updateQuestion(Question question) throws SQLException;
}
