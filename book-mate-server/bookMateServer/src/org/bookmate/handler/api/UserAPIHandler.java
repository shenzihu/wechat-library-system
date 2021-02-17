package org.bookmate.handler.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bookmate.entities.User;
import org.bookmate.service.OrderService;
import org.bookmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 用户相关操作API
 *
 * @author Tiger
 */
@Controller
public class UserAPIHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    /**
     * 用户登录接口
     *
     * @param phone 用户名
     * @param password 密码
     * @return 1:登录成功 0:登录失败
     */
    @ResponseBody
    @RequestMapping(value = "api-user-login")
    public User login(@RequestParam("phone") String phone,
                      @RequestParam("password") String password, HttpServletRequest request) throws IOException {
        boolean loginSuccess = userService.login(phone, password);
        User user = null;
        if (loginSuccess) {
            user = userService.getUserByName(phone);
            request.getSession().setAttribute("user", user);
        }
        return user;
    }

    /**
     * 用户注册接口
     *
     * @param username  用户名
     * @param password  密码
     * @param password2 确认密码
     * @return 1:注册成功 0:注册失败
     */
    @ResponseBody
    @RequestMapping(value = "api-user-register")
    public String register(@RequestParam("phone") String phone,
            @RequestParam("username") String username,
                           @RequestParam("realName") String realName,
                           @RequestParam("password") String password,
                           @RequestParam("password2") String password2,
                           @RequestParam("department") String department
    ) {
        boolean registerSuccess = userService.register(username, password, password2, realName,department,phone);
        if (registerSuccess) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * 退出登录接口
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "api-user-exit")
    public String exit(HttpSession session) {
        session.removeAttribute("user");
        return "1";
    }

    /**
     * 修改用户信息接口
     *
     * @return 1:成功 0:失败
     */
    @ResponseBody
    @RequestMapping(value = "api-user-edit")
    public String edit(@RequestParam("id") Integer id, @RequestParam("username") String username, @RequestParam("password") String password,
                       @RequestParam("realName") String realName
    ) {
        if (!userService.editUser(id, username, password, realName)) {
            return "0";
        }
        return "1";
    }

    /**
     * 获取用户已借阅数
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "api-user-order-number/{userId}")
    public Integer getBorrowNumber(@PathVariable("userId") Integer userId) {
        return orderService.getOrderByUserAndStatus(userId, 1).size() + orderService.getOrderByUserAndStatus(userId, 3).size();
    }

}
