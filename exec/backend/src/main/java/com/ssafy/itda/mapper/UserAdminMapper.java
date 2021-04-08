package com.ssafy.itda.mapper;

import com.ssafy.itda.domain.UserAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface UserAdminMapper {
    public List<UserAdmin> getUserByAdminId(int adminId) throws SQLException;
    public List<UserAdmin> getAdminByUserId(int userId) throws SQLException;
    public int postUserAdmin(UserAdmin userAdmin) throws SQLException;
}
