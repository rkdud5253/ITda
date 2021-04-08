package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.AccessCheck;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface AccessCheckMapper {
    public AccessCheck getAccessCheckByUserName(String userName) throws SQLException;
    public AccessCheck getAccessCheckByAdminId(int adminId) throws SQLException;
    public int postAccessCheck(AccessCheck accessCheck) throws SQLException;
    public int deleteAccessCheck (int adminId) throws SQLException;
}