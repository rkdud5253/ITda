package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.WrongQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface WrongQuestionMapper {

    public WrongQuestion getWrongQuestion(WrongQuestion wrongQuestion) throws SQLException;
    public List<WrongQuestion> getWrongQuestionList(int userId) throws SQLException;
    public int postWrongQuestion(WrongQuestion wrongQuestion) throws SQLException;
    public int deleteWrongQuestion(WrongQuestion wrongQuestion) throws SQLException;
}
