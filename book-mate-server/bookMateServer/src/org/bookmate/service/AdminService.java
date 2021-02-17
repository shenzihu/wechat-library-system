package org.bookmate.service;

import java.util.List;

import org.bookmate.entities.Admin;

/**
 * 管理员业务逻辑接口层
 * @author Tiger
 */
public interface AdminService {
	
	/**
	 * 管理员登录
	 * @param name 管理员名
	 * @param password 密码
	 * @return 0失败, 1成功
	 */
	public int login(String name, String password);
	
	/**
	 * 通过管理员名获取管理员实体类
	 * @param name 管理员名
	 * @return admin 管理员实体类
	 */
	public Admin getAdminByName(String name);
	
	/**
	 *添加管理员
	 * @param name 管理员名
	 * @param password 密码
	 * @param password2 再次输入密码
	 * @param work 职能
	 * @param email 邮箱
	 * @return false:添加失败 true:添加成功
	 */
	public boolean addAdmin(String name, String password, String password2, String work, String email);
	
	/**
	 * 获取全部管理员
	 * @return admins 管理员实体类集合
	 */
	public List<Admin> getAllAdmin();
	
	/**
	 * 删除管理员
	 * @param id 管理员id
	 * @return true:删除成功 false:删除失败
	 */
	public boolean removeAdmin(Integer id);
	
	/**
	 * 根据id获取管理员
	 * @param id 管理员id
	 * @return admin 管理员实体类
	 */
	public Admin getAdminById(Integer id);
	
	/**
	 * 修改管理员信息
	 * @param id 管理员id
	 * @param name 管理员名
	 * @param password 密码
	 * @param email 邮箱
	 * @param work 职能
	 * @return true:修改成功 false:修改失败
	 */
	public boolean editAdmin(Integer id, String name, String password, String email, String work);
	
	
	/**
	 * 通过管理员名模糊查询管理员实体类
	 * @param name 管理员名
	 * @return admins 管理员实体类集合
	 */
	public List<Admin> getAdminByNameLike(String name);
	
	
}
