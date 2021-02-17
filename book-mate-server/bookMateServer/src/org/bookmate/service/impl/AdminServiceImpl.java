package org.bookmate.service.impl;

import java.util.Date;
import java.util.List;

import org.bookmate.dao.AdminDao;
import org.bookmate.entities.Admin;
import org.bookmate.service.AdminService;
import org.bookmate.util.EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员业务逻辑实现类
 * @author Tiger
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public int login(String name, String password) {
		Admin admin = adminDao.selectAdminByUsername(name);
		//判断用户名是否为空
		if (admin == null) {
			return 0;
		} else {
			//判断密码是否正确
			String passwordMD5 = EncoderUtil.EncoderByMd5(password);
			if (!admin.getAdminPassword().equals(passwordMD5)) {
				return 0;
			}
			return 1;
		}
	}

	@Override
	public Admin getAdminByName(String name) {
		return adminDao.selectAdminByUsername(name);
	}

	@Override
	public boolean addAdmin(String name, String password, String password2, String work, String email) {
		if ("".equals(name) || "".equals(password) || "".equals(password2) || "".equals(work) || "".equals(email)) {
			return false;
		} else {
			if (!password.equals(password2)) {
				return false;
			} else {
				if (adminDao.selectAdminByUsername(name) != null) {
					return false;
				}
				Admin admin = new Admin();
				admin.setAdminUsername(name);
				admin.setAdminPassword(EncoderUtil.EncoderByMd5(password));
				admin.setAdminWork(work);
				admin.setAdminEmail(email);
				admin.setAdminCreateTime(new Date());
				adminDao.insertAdmin(admin);
				return true;
			}
		}
	}

	@Override
	public List<Admin> getAllAdmin() {
		return adminDao.selectAllAdmin();
	}

	@Override
	public boolean removeAdmin(Integer id) {
		if (id <= 0 || id == null) {
			return false;
		}
		adminDao.deleteAdmin(id);
		return true;
	}

	@Override
	public Admin getAdminById(Integer id) {
		return adminDao.selectAdminById(id);
	}

	@Override
	public boolean editAdmin(Integer id, String name, String password, String email, String work) {
		if (id == null || "".equals(name) || "".equals(email) || "".equals(work) || "".equals(password)) {
			return false;
		}
		Admin admin = this.getAdminById(id);
		if (!password.equals(admin.getAdminPassword())) {
			admin.setAdminPassword(EncoderUtil.EncoderByMd5(password));
		}
		admin.setAdminUsername(name);
		admin.setAdminEmail(email);
		admin.setAdminWork(work);
		adminDao.updateAdmin(admin);
		return true;
	}

	@Override
	public List<Admin> getAdminByNameLike(String name) {
		return adminDao.selectAdminByUsernameLike(name);
	}


}
