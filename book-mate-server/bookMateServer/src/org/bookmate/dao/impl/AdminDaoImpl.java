package org.bookmate.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bookmate.dao.AdminDao;
import org.bookmate.entities.Admin;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 管理员数据访问层实现类
 * 
 * @author Tiger
 */
@Repository
public class AdminDaoImpl extends SqlSessionDaoSupport implements AdminDao {

	@SuppressWarnings("unchecked")
	@Override
	public Admin selectAdminByUsername(String name) {
		ArrayList<Admin> admins = (ArrayList<Admin>) this.getSqlSession()
				.selectList(ADMIN_NAMESPACE + "selectAdminByUsername", name);
		if (admins.isEmpty()) {
			return null;
		} else {
			return admins.get(0);
		}
	}

	@Override
	public void insertAdmin(Admin admin) {
		this.getSqlSession().insert(ADMIN_NAMESPACE + "insertAdmin", admin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> selectAllAdmin() {
		return this.getSqlSession().selectList(ADMIN_NAMESPACE + "selectAllAdmin");
	}

	@Override
	public void deleteAdmin(Integer id) {
		this.getSqlSession().delete(ADMIN_NAMESPACE + "deleteAdmin", id);
	}

	@Override
	public Admin selectAdminById(Integer id) {
		return (Admin) this.getSqlSession().selectOne(ADMIN_NAMESPACE + "selectAdminById", id);
	}

	@Override
	public void updateAdmin(Admin admin) {
		this.getSqlSession().update(ADMIN_NAMESPACE + "updateAdmin", admin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> selectAdminByUsernameLike(String name) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("name", name);
		ArrayList<Admin> admins = (ArrayList<Admin>) this.getSqlSession()
				.selectList(ADMIN_NAMESPACE + "selectAdminByUsernameLike", queryMap);
		return admins;
	}

}
