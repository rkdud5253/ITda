package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.UserAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface UserAdminMapper {
    public UserAdmin getUserAdmin(int userAdminId) throws SQLException;
}
