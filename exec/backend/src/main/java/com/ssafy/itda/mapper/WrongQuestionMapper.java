package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.WrongQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface WrongQuestionMapper {

    public WrongQuestion getWrongQuestion(int userId, int questionId) throws SQLException;
    public List<WrongQuestion> getWrongQuestionList(int userId) throws SQLException;
    public boolean postWrongQuestion(WrongQuestion wrongQuestion) throws SQLException;
    public boolean deleteWrongQuestion(WrongQuestion wrongQuestion) throws SQLException;
}
