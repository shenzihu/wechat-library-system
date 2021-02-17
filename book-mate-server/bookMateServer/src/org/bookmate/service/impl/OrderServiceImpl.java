package org.bookmate.service.impl;

import java.util.*;

import org.bookmate.dao.BookDao;
import org.bookmate.dao.OrderDao;
import org.bookmate.dao.UserDao;
import org.bookmate.entities.Book;
import org.bookmate.entities.Order;
import org.bookmate.entities.User;
import org.bookmate.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预定相关业务逻辑实现类
 *
 * @author Tiger
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addOrder(Integer bookId, Integer userId, Integer num) {
        //保存借阅记录
        Order order = new Order();
        order.setBookId(bookId);
        order.setUserId(userId);
        order.setNum(num);
        orderDao.insertOrder(order);
        return true;
    }

    @Override
    public List<Order> getOrderByUserAndBookAndStatus(Integer bookId, Integer userId, Integer status) {
        return orderDao.selectOrderByUserAndBookAndStatus(userId, bookId, status);
    }

    @Override
    public void removeOrderByUserAndBookAndStatus(Integer bookId, Integer userId, Integer status) {
        orderDao.deleteOrderByUserAndBookAndStatus(userId, bookId, status);
    }

    @Override
    public List<Order> getOrderByUserAndStatus(Integer userId, Integer status) {
        return orderDao.selectOrderByUserAndStatus(userId, status);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderDao.selectOrderById(id);
    }

    @Override
    public void adminUpdateOrder(Order order) {
        Order ord = orderDao.selectOrderById(order.getId());
        ord.setNum(order.getNum());
        ord.setBookId(order.getBookId());
        ord.setStatus(order.getStatus());
        ord.setUserId(order.getUserId());
        orderDao.updateOrder(ord);
    }

    @Override
    public void adminCheckOrder(int id, int status) {
        Order ord = orderDao.selectOrderById(id);
        ord.setStatus(status);
        orderDao.updateOrder(ord);
    }

    @Override
    public Integer getOrderCountByStatus(int status) {
        return orderDao.selectOrderCountByStatus(status);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderDao.selectAllOrder();
    }

    @Override
    public List<Order> getOrderByStatus(int status) {
        return orderDao.getOrderByStatus(status);
    }


    @Override
    public void deleteReserveBook(Integer userId, Integer bookId) {
        //将可借阅图书加1
        Book book = bookDao.selectBookById(bookId);
        bookDao.updateBook(book);
        this.removeOrderByUserAndBookAndStatus(bookId, userId, 0);
    }

    @Override
    public List<Order> seachOrder(String content) {
        return orderDao.selectOrderByUserOrBookLike(content);
    }

    @Override
    public void saveOrders(Map<Integer, Integer> map, Integer userId) {
        map.forEach((key, value) -> {
            Order order = new Order();
            order.setNum(value);
            order.setBookId(key);
            order.setUserId(userId);
            if(value != 0){
                orderDao.insertOrder(order);
            }
        });
    }

}
