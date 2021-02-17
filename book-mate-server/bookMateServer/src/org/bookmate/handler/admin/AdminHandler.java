package org.bookmate.handler.admin;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.bookmate.entities.Admin;
import org.bookmate.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 后台管理员页面控制器
 * @author Tiger
 */
@Controller
public class AdminHandler {
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 管理员列表显示
	 * @return admin/admin_list.jsp
	 */
	@RequestMapping(value="admin-admin-list-show", method=RequestMethod.GET)
	public String listShow(Map<String, Object> requestMap, @RequestParam("page") Integer page) {
		requestMap.put("nav", "admin-list");
		ArrayList<Admin> admins = (ArrayList<Admin>) adminService.getAllAdmin();
		requestMap.put("admins", admins);
		
		int pageCount = admins.size();  //数据条数
		int pageSize = 10;  //分页条数
		int pageMax = pageCount / pageSize;  //最大页数
		int pagePointer = 1;  //当前指向页
		if (pageMax != 0 && pageCount % pageSize != 0) {
			++pageMax;
		}
		if (pageMax == 0) {
			pageMax = 1;
		}
		if (page < 1 || page > pageMax) {
			pagePointer = 1;
		} else {
			pagePointer = page;
		}
		ArrayList<Admin> pageAdmins = new ArrayList<>();
		if (pageMax == 1) {
			pageAdmins.addAll(admins);
		} else if (pagePointer == pageMax) {
			for (int i = pageSize * (pagePointer - 1); i < pageSize * (pagePointer - 1) + (pageCount % pageSize); ++i) {
				pageAdmins.add(admins.get(i));
			}
		} else {
			for (int i = pageSize * (pagePointer - 1); i < pageSize * pagePointer; ++i) {
				pageAdmins.add(admins.get(i));
			}
		}
		requestMap.put("pageMax", pageMax);
		requestMap.put("pagePoint", pagePointer);
		requestMap.put("pageAdmins", pageAdmins);
		
		return "admin/admin_list";
	}
	
	/**
	 * 搜索管理员显示
	 * @return admin/admin_list.jsp
	 */
	@RequestMapping(value="admin-admin-searchadmin-show", method=RequestMethod.GET)
	public String searchAdminShow(Map<String, Object> requestMap, @RequestParam("name") String name) {
		requestMap.put("nav", "admin-list");
		ArrayList<Admin> admins = (ArrayList<Admin>) adminService.getAdminByNameLike(name);
		requestMap.put("pageAdmins", admins);
		return "admin/admin_list";
	}
	
	
	/**
	 * 新增管理员显示
	 * @return "admin/admin_register.jsp"
	 */
	@RequestMapping(value="admin-admin-register-show", method=RequestMethod.GET)
	public String registerShow() {
		return "admin/admin_register";
	}
	
	/**
	 * 新增管理员执行
	 * @return admin/admin_list.jsp
	 */
	@RequestMapping(value="admin-admin-register-execute", method=RequestMethod.POST)
	public String registerExecute(@RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("password2") String password2, 
			@RequestParam("work") String work, @RequestParam("email") String email) {
		System.out.println(name);
		boolean registerSuccess = adminService.addAdmin(name, password, password2, work, email);
		String view = "redirect:/admin-admin-list-show?page=1";
		if (!registerSuccess) {
			view = "admin/admin_register"; 
		}
		return view;
	}
	
	/**
	 * 管理员授权页面
	 * @return admin/admin_password.jsp
	 */
	@RequestMapping(value="admin-admin-password-show", method=RequestMethod.GET)
	public String passwordShow() {
		return "admin/admin_password";
	}
	
	/**
	 * 管理员授权处理
	 * @return admin/admin_list.jsp
	 */
	@RequestMapping(value="admin-admin-password-execute", method=RequestMethod.POST)
	public String passwordExecute(Map<String, Object> requestMap, HttpSession session, 
			@RequestParam String password) {
		if ("admin".equals(password)) {
			session.setAttribute("advanced", 1);
		}
		return "redirect:/admin-admin-list-show?page=1";
	}
	
	/**
	 * 删除管理员执行
	 * @return admin/admin_list.jsp
	 */
	@RequestMapping(value="admin-admin-removeadmin-execute/{id}", method=RequestMethod.GET)
	public String removeAdminExecute(@PathVariable Integer id) {
		adminService.removeAdmin(id);
		return "redirect:/admin-admin-list-show?page=1";
	}
	
	/**
	 * 修改管理员信息显示
	 * @return admin/admin_list_edit.jsp
	 */
	@RequestMapping(value="admin-admin-editadmin-show/{id}", method=RequestMethod.GET)
	public String editAdminShow(Map<String, Object> requestMap, @PathVariable Integer id) {
		Admin admin = adminService.getAdminById(id);
		requestMap.put("admin", admin);
		return "admin/admin_list_edit";
	}
	
	/**
	 * 修改管理员信息执行
	 * @return admin/admin_message.jsp
	 */
	@RequestMapping(value="admin-admin-editadmin-execute", method=RequestMethod.POST)
	public String editAdminExecute(@RequestParam("id") Integer id, 
			@RequestParam("name") String name, @RequestParam("password") String password, 
			@RequestParam("email") String email, @RequestParam("work") String work) {
		boolean editSuccess = adminService.editAdmin(id, name, password, email, work);
		String view = "redirect:/admin-admin-list-show?page=1";
		if (!editSuccess) {
			view = "redirect:/admin-admin-editadmin-show/" + id;
		}
		return view;
	}	
	
}
