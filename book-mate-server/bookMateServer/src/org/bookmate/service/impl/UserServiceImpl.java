package org.bookmate.service.impl;

import java.util.Date;
import java.util.List;

import org.bookmate.dao.UserDao;
import org.bookmate.entities.User;
import org.bookmate.service.UserService;
import org.bookmate.util.EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户相关业务逻辑层
 *
 * @author Tiger
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(String phone, String password) {
        if ("".equals(phone) || "".equals(password)) {
            return false;
        }
        User user = userDao.selectUserByName(phone);
        if (user == null) {
            return false;
        } else {
            if (user.getUserPassword().equals(EncoderUtil.EncoderByMd5(password))) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public User getUserByName(String phone) {
        return userDao.selectUserByName(phone);
    }

    @Override
    public boolean register(String username, String password, String password2, String realName, String department,String phone) {
        if ("".equals(username) || "".equals(password) || "".equals(password2) || "".equals(realName)) {
            return false;
        }
        if (!password.equals(password2)) {
            return false;
        }
        if (userDao.selectUserByName(username) != null) {
            return false;
        }
        User user = new User();
        user.setUserUsername(username);
        user.setUserPassword(EncoderUtil.EncoderByMd5(password));
        user.setUserCreateTime(new Date());
        user.setRealName(realName);
        user.setDepartment(department);
        user.setPhone(phone);
        userDao.insertUser(user);
        return true;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public boolean removeUser(Integer id) {
        if (id <= 0 || id == null) {
            return false;
        }
        userDao.deleteUser(id);
        return true;
    }

    @Override
    public List<User> getUserByUserNameLike(String username) {
        return userDao.selectUserByUserNameLike(username);
    }

    @Override
    public boolean editUser(Integer id, String username, String password, String realName) {
        User user = userDao.selectUserById(id);
        if ("".equals(username) || "".equals(password)) {
            return false;
        }
        if (!password.equals(user.getUserPassword())) {
            password = EncoderUtil.EncoderByMd5(password);
            user.setUserPassword(password);
        }
        user.setUserUsername(username);
        user.setRealName(realName);
        userDao.updateUser(user);
        return true;
    }

    @Override
    public Integer getUserCount() {
        return userDao.selectUserCount();
    }

    @Override
    public void resetCredit(Integer userId) {
        User user = userDao.selectUserById(userId);
        userDao.updateUser(user);
    }

}
