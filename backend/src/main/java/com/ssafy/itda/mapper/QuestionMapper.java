package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.Question;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface QuestionMapper {

  public Question getQuestion(int questionId) throws SQLException;
  public List<Question> getQuestionList(int userId) throws SQLException;
  public int questionGetSize() throws SQLException;
  public boolean postQuestion(Question question) throws SQLException;
  public boolean updateQuestion(Question question) throws SQLException;
}
