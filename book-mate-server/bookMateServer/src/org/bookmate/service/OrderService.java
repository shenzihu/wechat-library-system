package org.bookmate.service;

import java.util.List;
import java.util.Map;

import org.bookmate.entities.Order;

/**
 * 预定相关业务逻辑接口层
 *
 * @author Tiger
 */
public interface OrderService {

    /**
     * 增加预定图书
     *
     * @param bookId 图书id
     * @param userId 用户id
     * @param num    预定数量
     */
    public boolean addOrder(Integer bookId, Integer userId, Integer num);

    /**
     * 通过用户id,图书id,状态获取预定信息
     *
     * @param bookId
     * @param userId
     * @param status
     */
    public List<Order> getOrderByUserAndBookAndStatus(Integer bookId, Integer userId, Integer status);

    /**
     * 通过用户id,图书id,状态删除预定信息
     *
     * @param bookId
     * @param userId
     * @param status
     * @return
     */
    public void removeOrderByUserAndBookAndStatus(Integer bookId, Integer userId, Integer status);

    /**
     * 通过用户id和状态获取预定信息
     *
     * @param userId
     * @param status
     * @return
     */
    public List<Order> getOrderByUserAndStatus(Integer userId, Integer status);

    /**
     * 通过id获取预定信息
     *
     * @param id
     * @return
     */
    public Order getOrderById(Integer id);

    /**
     * 管理员更新Order
     *
     */
    public void adminUpdateOrder(Order order);

    /**
     * 审核
     *
     * @param id
     * @param status
     */
    public void adminCheckOrder(int id, int status);

    /**
     * 获取预定数量
     *
     * @return
     */
    public Integer getOrderCountByStatus(int status);


    public List<Order> getAllOrder();

    public List<Order> getOrderByStatus(int status);

    /**
     * 删除预订信息
     *
     * @param userId
     * @param bookId
     */
    public void deleteReserveBook(Integer userId, Integer bookId);

    public List<Order> seachOrder(String content);

    void saveOrders(Map<Integer, Integer> map, Integer userId);
}
