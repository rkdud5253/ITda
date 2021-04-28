package com.ssafy.itda.service;

import com.ssafy.itda.domain.WrongQuestion;
import com.ssafy.itda.mapper.WrongQuestionMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WrongQuestionService {
    private final SqlSession sqlSession;

    @Autowired
    public WrongQuestionService(SqlSession sqlSession) { this.sqlSession = sqlSession; }

    public WrongQuestion getWrongQuestion(WrongQuestion wrongQuestion) throws Exception{
        return sqlSession.getMapper(WrongQuestionMapper.class).getWrongQuestion(wrongQuestion);
    }

    public List<WrongQuestion> getWrongQuestionList(int userId) throws Exception{
        return sqlSession.getMapper(WrongQuestionMapper.class).getWrongQuestionList(userId);
    }

    public boolean postWrongQuestion(WrongQuestion wrongQuestion) throws Exception{
        return sqlSession.getMapper(WrongQuestionMapper.class).postWrongQuestion(wrongQuestion) == 1;
    }

    public boolean deleteWrongQuestion(WrongQuestion wrongQuestion) throws Exception{
        return sqlSession.getMapper(WrongQuestionMapper.class).deleteWrongQuestion(wrongQuestion) == 1;
    }
}
