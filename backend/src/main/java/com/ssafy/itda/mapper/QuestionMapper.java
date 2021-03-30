package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

  public Question getQuestion(int questionId) throws Exception;
  public List<Question> getQuestionList(int userId) throws Exception;
  public int questionGetSize() throws Exception;
  public boolean postQuestion(Question question) throws Exception;
  public boolean updateQuestion(Question question) throws Exception;
}
