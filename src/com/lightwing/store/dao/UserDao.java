package com.lightwing.store.dao;

import com.lightwing.store.domain.User;

import java.sql.SQLException;


public interface UserDao {
    User findUserByUsreName(String um) throws SQLException;

    void userRegist(User user01) throws SQLException;

    User userActive(String code) throws SQLException;

    void updateUser(User user01) throws SQLException;

    User userLogin(User user01) throws SQLException;
}