package com.lightwing.store.service.serviceImp;

import com.lightwing.store.dao.UserDao;
import com.lightwing.store.domain.User;
import com.lightwing.store.service.UserService;
import com.lightwing.store.utils.BeanFactory;
import com.lightwing.store.utils.MailUtils;

import java.sql.SQLException;

public class UserServiceImp implements UserService {
    com.lightwing.store.dao.UserDao UserDao = (UserDao) BeanFactory.createObject("UserDao");

    @Override
    public User userLogin(User user01) throws SQLException {
        return UserDao.userLogin(user01);
    }

    @Override
    public User findUserByUsreName(String um) throws SQLException {

        return UserDao.findUserByUsreName(um);

    }

    @Override
    public void userRegist(User user01) throws SQLException {
        // 3_调用业务层注册功能
        UserDao.userRegist(user01);
        try {
            // 向用户邮箱发送一份激活邮件
            MailUtils.sendMail(user01.getEmail(), user01.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User userActive(String code) throws SQLException {
        return UserDao.userActive(code);
    }

    @Override
    public void updateUser(User user01) throws SQLException {
        UserDao.updateUser(user01);
    }
}
