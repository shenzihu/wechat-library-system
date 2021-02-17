package org.bookmate.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bookmate.dao.OrderDao;
import org.bookmate.entities.Order;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 预定数据访问实现类
 *
 * @author Tiger
 */
@Repository
public class OrderDaoImpl extends SqlSessionDaoSupport implements OrderDao {

    @Override
    public void insertOrder(Order order) {
        this.getSqlSession().insert(ORDER_NAMESPACE + "insertOrder", order);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> selectOrderByUserAndBookAndStatus(Integer userId, Integer bookId, Integer status) {
        Map<String, Integer> queryMap = new HashMap<>();
        queryMap.put("userId", userId);
        queryMap.put("bookId", bookId);
        queryMap.put("status", status);
        ArrayList<Order> orders = (ArrayList<Order>) this.getSqlSession()
                .selectList(ORDER_NAMESPACE + "selectOrderByUserAndBookAndStatus", queryMap);
        return orders;
    }

    @Override
    public void deleteOrderByUserAndBookAndStatus(Integer userId, Integer bookId, Integer status) {
        Map<String, Integer> queryMap = new HashMap<>();
        queryMap.put("userId", userId);
        queryMap.put("bookId", bookId);
        queryMap.put("status", status);
        this.getSqlSession().update(ORDER_NAMESPACE + "deleteOrderByUserAndBookAndStatus", queryMap);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> selectOrderByUserAndStatus(Integer userId, Integer status) {
        Map<String, Integer> queryMap = new HashMap<>();
        queryMap.put("userId", userId);
        queryMap.put("status", status);
        return this.getSqlSession().selectList(ORDER_NAMESPACE + "selectOrderByUserAndStatus", queryMap);
    }

    @Override
    public Order selectOrderById(Integer id) {
        return (Order) this.getSqlSession().selectOne(ORDER_NAMESPACE + "selectOrderById", id);
    }

    @Override
    public void updateOrder(Order order) {
        this.getSqlSession().update(ORDER_NAMESPACE + "updateOrder", order);
    }

    @Override
    public Integer selectOrderCountByStatus(int status) {
        Object result = this.getSqlSession().selectOne(ORDER_NAMESPACE + "selectOrderCountByStatus", status);
        return result == null ? 0 : (Integer) result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> selectAllOrder() {
        return this.getSqlSession().selectList(ORDER_NAMESPACE + "selectAllOrder");
    }

    @Override
    public void deleteOrderById(Integer id) {
        this.getSqlSession().update(ORDER_NAMESPACE + "deleteOrderById", id);
    }

    @Override
    public int selectAllReserveOrder() {
        Object result = this.getSqlSession().selectOne(ORDER_NAMESPACE + "selectAllReserveOrder");
        return result == null ? 0 : (Integer) result;
    }

    @Override
    public List<Order> selectOrderByUserOrBookLike(String content) {
        Map<String, String> map = new HashMap<>();
        map.put("content", content);
        return this.getSqlSession().selectList(ORDER_NAMESPACE + "selectOrderByUserOrBookLike", map);
    }

    @Override
    public List<Order> getOrderByStatus(int status) {
        return this.getSqlSession().selectList(ORDER_NAMESPACE + "selectOrderByStatus",status);
    }

}
