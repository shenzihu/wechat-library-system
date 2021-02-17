package org.bookmate.handler.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.aspectj.weaver.ast.Or;
import org.bookmate.entities.Admin;
import org.bookmate.entities.Order;
import org.bookmate.service.AdminService;
import org.bookmate.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 图书预定相关接口
 *
 * @author Tiger
 */
@Controller
public class OrderAPIHandler {

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderService orderService;

    /**
     * 管理员登录接口
     *
     * @param name     管理员名
     * @param password 管理员密码
     * @return admin 管理员实体类
     */
    @ResponseBody
    @RequestMapping(value = "api-scan-admin-login")
    public Admin adminLogin(@RequestParam("name") String name, @RequestParam("password") String password,
                            HttpSession session) {
        int loginSuccess = adminService.login(name, password);
        if (loginSuccess == 0) {
            return null;
        }
        Admin admin = adminService.getAdminByName(name);
        session.setAttribute("admin", admin);
        return admin;
    }

    /**
     * 通过用户id和图书id获取预定主键
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "api-order-book-count/{userId}/{status}")
    public int getOrderByUserId(@PathVariable("userId") Integer userId, @PathVariable("status") Integer status) {
        List<Order> orderList = orderService.getOrderByUserAndStatus(userId, status);
        return orderList.size();
    }

    /**
     * 通过id获取预定信息接口
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "api-scan-borrow-byid/{id}")
    public Order getBorrowById(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id);
    }


    /**
     * 获取预定历史接口
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "api-order-history/{id}")
    public List<Order> getOrderHistory(@PathVariable("id") Integer userId) {
        List<Order> results = new ArrayList<>();
        results.addAll(orderService.getOrderByUserAndStatus(userId, 1));
        results.addAll(orderService.getOrderByUserAndStatus(userId, 2));
        results.addAll(orderService.getOrderByUserAndStatus(userId, 3));
        return results;
    }

    /**
     * 删除预订图书信息
     *
     * @param bookId
     * @param userId
     */
    @ResponseBody
    @RequestMapping(value = "api-book-remove-reserve/{bookId}/{userId}")
    public int removeReserveBook(@PathVariable("bookId") Integer bookId, @PathVariable("userId") Integer userId) {
        orderService.deleteReserveBook(userId, bookId);
        return 1;
    }


    @ResponseBody
    @RequestMapping(value = "api-order-addOrder/{userId}", method = RequestMethod.POST)
    public int addOrder(@PathVariable("userId") Integer userId, @RequestBody Map<Integer, Integer> map) {
        orderService.saveOrders(map, userId);
        return 1;
    }
}
