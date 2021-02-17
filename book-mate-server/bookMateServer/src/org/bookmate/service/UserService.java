package org.bookmate.service;

import java.util.List;

import org.bookmate.entities.User;

/**
 * 用户相关业务逻辑接口层
 *
 * @author Tiger
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @return true:登录成功 false:登录失败
     */
    public boolean login(String phone, String password);

    /**
     * 通过用户名获取用户信息
     *
     * @param phone 用户名
     * @return user 用户实体类
     */
    public User getUserByName(String phone);

    /**
     * 用户注册
     *
     * @param username  用户名
     * @param realName  真实姓名
     * @param password  密码
     * @param password2 确认密码
     * @param department 部门
     * @param phone 电话登录名
     * @return true:成功 false:失败
     */
    public boolean register(String username, String password, String password2, String realName, String department,String phone);

    /**
     * 获取所有用户
     *
     * @return users 用户实体类集合
     */
    public List<User> getAllUser();

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return true:删除成功 false:删除失败
     */
    public boolean removeUser(Integer id);

    /**
     * 通过用户名模糊查询管理员通知实体类
     *
     * @param username 用户名
     * @return users 用户实体类集合
     */
    public List<User> getUserByUserNameLike(String username);

    /**
     * 编辑用户信息
     *
     * @param id       用户id
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean editUser(Integer id, String username, String password, String realName);

    /**
     * 获取用户数量
     *
     * @return
     */
    public Integer getUserCount();

    /**
     * 信用重置
     *
     * @param userId
     */
    public void resetCredit(Integer userId);

}
