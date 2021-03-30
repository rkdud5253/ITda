package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.WrongQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WrongQuestionMapper {

    public WrongQuestion getWrongQuestion(int userId, int questionId) throws Exception;
    public List<WrongQuestion> getWrongQuestionList(int userId) throws Exception;
    public boolean postWrongQuestion(WrongQuestion wrongQuestion) throws Exception;
    public boolean deleteWrongQuestion(WrongQuestion wrongQuestion) throws Exception;
}
