package org.bookmate.dao;

import java.util.List;

import org.bookmate.entities.Order;

/**
 * 预定相关数据访问接口层
 * @author Tiger
 */
public interface OrderDao {

	public static final String ORDER_NAMESPACE = "org.bookmate.mapper.OrderMapper.";

	/**
	 * 插入一条新预定记录
	 * @param order 预定图书实体类
	 */
	public void insertOrder(Order order);

	/**
	 * 通过用户id,图书id,状态查找预定记录
	 * @param userId
	 * @param bookId
	 * @return
	 */
	public List<Order> selectOrderByUserAndBookAndStatus(Integer userId, Integer bookId, Integer status);

	/**
	 * 通过用户id,图书id,状态删除预定记录
	 * @param userId
	 * @param bookId
	 * @param status
	 */
	public void deleteOrderByUserAndBookAndStatus(Integer userId, Integer bookId, Integer status);

	/**
	 * 通过用户id,状态查找预定记录
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Order> selectOrderByUserAndStatus(Integer userId, Integer status);

	/**
	 * 通过id获取预定信息
	 * @param id
	 * @return
	 */
	public Order selectOrderById(Integer id);

	/**
	 * 更新预定信息
	 */
	public void updateOrder(Order order);

	/**
	 * 获取预定数量
	 * @return
	 */
	public Integer selectOrderCountByStatus(int status);

	/**
	 * 查找所有预定
	 * @return
	 */
	public List<Order> selectAllOrder();

	/**
	 * 通过id删除预定信息
	 * @param id
	 */
	public void deleteOrderById(Integer id);

	/**
	 * 统计预定成功人的数量
	 * @return
	 */
	public int selectAllReserveOrder();


    List<Order> selectOrderByUserOrBookLike(String content);

    List<Order> getOrderByStatus(int status);
}
