package com.ssafy.itda.service;

import com.ssafy.itda.domain.Question;
import com.ssafy.itda.mapper.QuestionMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

  private final SqlSession sqlSession;

  @Autowired
  public QuestionService(SqlSession sqlSession) { this.sqlSession = sqlSession; }

  public Question getQuestion(int questionId) throws Exception{
    return sqlSession.getMapper(QuestionMapper.class).getQuestion(questionId);
  }

  public List<Question> getQuestionList(int userId) throws Exception{
    return sqlSession.getMapper(QuestionMapper.class).getQuestionList(userId);
  }

  public int questionGetSize() throws Exception{
    return sqlSession.getMapper(QuestionMapper.class).questionGetSize();
  }

  public boolean postQuestion(Question question) throws Exception{
    return sqlSession.getMapper(QuestionMapper.class).postQuestion(question);
  }

  public boolean updateQuestion(Question question) throws Exception{
    return sqlSession.getMapper(QuestionMapper.class).updateQuestion(question);
  }
}
