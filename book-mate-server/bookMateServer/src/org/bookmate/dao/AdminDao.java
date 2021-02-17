package org.bookmate.dao;

import java.util.List;

import org.bookmate.entities.Admin;

/**
 * 管理员数据访问接口层
 * 
 * @author Tiger
 *
 */

public interface AdminDao {

	public static final String ADMIN_NAMESPACE = "org.bookmate.mapper.AdminMapper.";

	/**
	 * 通过名称查询管理员实体类
	 * 
	 * @param name 管理员名
	 * @return admin 管理员实体类
	 */
	public Admin selectAdminByUsername(String name);

	/**
	 * 插入管理员
	 * 
	 * @param admin 管理员实体类
	 */
	public void insertAdmin(Admin admin);

	/**
	 * 查询所有的管理员实体类
	 * 
	 * @return admins 管理员实体类集合
	 */
	public List<Admin> selectAllAdmin();

	/**
	 * 根据id删除对应管理员
	 * 
	 * @param id 管理员id
	 */
	public void deleteAdmin(Integer id);

	/**
	 * 根据id查询管理员实体类
	 * 
	 * @param id
	 * @return
	 */
	public Admin selectAdminById(Integer id);

	/**
	 * 更新管理员信息
	 * 
	 * @param admin 管理员实体类
	 */
	public void updateAdmin(Admin admin);

	/**
	 * 通过名称模糊查询管理员实体类
	 * 
	 * @param name 管理员名
	 * @return admins 管理员实体类结合
	 */
	public List<Admin> selectAdminByUsernameLike(String name);

}
