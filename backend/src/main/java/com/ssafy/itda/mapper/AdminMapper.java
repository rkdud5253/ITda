package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface AdminMapper {
    public Admin getAdmin(int adminId) throws SQLException;
    public int signUp(Admin admin) throws SQLException;
    public int login (Admin admin) throws SQLException;
}
