package com.pentair.showcase.common.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.pentair.showcase.catalog.entity.Series;
import com.pentair.showcase.common.dao.*;
import com.pentair.showcase.common.entity.*;
import com.pentair.showcase.common.service.AccountManager;
import com.pentair.showcase.security.LoginUser;
/**
 * 用户管理Action.
 * 
 * @author calvin
 */
@Namespace("/system")
@InterceptorRefs( { @InterceptorRef("paramsPrepareParamsStack") })
@Results({
	@Result(name = CrudActionSupport.RELOAD, location = "user.action", type = "redirect"),
	@Result(name = "password_input", location = "/WEB-INF/content/system/password_input.jsp")
})
public class UserAction extends CrudActionSupport<User> {

	private static final long serialVersionUID = 7240853226114035208L;

	private AccountManager accountManager;
	private RoleDao roleDao;
	private AreaDao areaDao;
	private UserDao userDao;
	private List<String> checkedRoles;
	

	//-- 页面属性  --//
	private String id;
	private String area_id;
	private String asm_id;
	private User entity;
	private List<User> allUserList;
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改	

	private List<String> checkedUserIds;
	
	private List<Area> areasAll;
	private List<User> asmsAll;

	LoginUser loginUser=(LoginUser)SpringSecurityUtils.getCurrentUser();

	//-- ModelDriven 与 Preparable函数 --//
	public User getModel() {
		return entity;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null && !id.equals("")) {
			entity = accountManager.getUser(id);
		} else {
			entity = new User();
		}
	}

	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		try {
			allUserList = accountManager.getAllUserWithRole();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		if(entity!=null){
			checkedRoles=new ArrayList<String>();
			for(Role role:entity.getRoles()){
				checkedRoles.add(role.getId());
			}
		}
		if(entity.getArea() != null){
			this.area_id=entity.getArea().getId();		
		}
		if(entity.getAsm() != null) {
			this.asm_id = entity.getAsm().getId();
		}
		return INPUT;
	}

	/**
	 * 保存用户时,演示Hibernate的version字段使用.
	 */
	@Override 
	public String save() throws Exception {

		if (id!=null&&!"".equals(id)&&workingVersion < entity.getVersion()) {
			throw new StaleStateException("操作对象已被其他用户修改，请重新操作！");
		}
		entity.setArea(areaDao.get(area_id));
		
		if(asm_id != null && asm_id.length() > 0) {
			entity.setAsm(userDao.getUserById(asm_id));
		}

		//更新用户角色
		List<Role> roles=new ArrayList<Role>();
		for(String roleId:checkedRoles){
			roles.add(roleDao.get(roleId));
		}

		entity.setRoles(roles);

		accountManager.saveUser(entity);
		
		addActionMessage("用户信息保存成功！");
		this.setPopup(true);//是弹出窗口
		return "result_success";
	}

	@Override
	public String delete() throws Exception {
		try {
			if (id != null) {
				entity = accountManager.getUser(id);
				accountManager.deleteUser(entity);
			}
		} catch (Exception e) {
			throw new StaleStateException("系统中有与该对象关联的数据，无法删除。");
		}
		return RELOAD;
	}

	//-- 其他Action函数 --//
	
	public String changePasswordInput() throws Exception {
		return "password_input";
	}
	
	public String changePasswordSave() throws Exception {
		System.out.println("==============="+loginUser);
		entity = accountManager.getUser(loginUser.getId());
		if(entity.getPlainPassword().equals(Struts2Utils.getRequest().getParameter("oldPassword"))){
			entity.setPlainPassword(Struts2Utils.getRequest().getParameter("plainPassword"));
			accountManager.saveUser(entity);
			addActionMessage("密码修改成功！");
			this.setPopup(true);//是弹出窗口
			return "result_success";
		}else{
			throw new StaleStateException("原密码输入有误，请重新输入！");
		}
	}
	
	public String disableUsers() {
		accountManager.disableUsers(checkedUserIds);
		return RELOAD;
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkLoginName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newLoginName = request.getParameter("loginName");
		String oldLoginName = request.getParameter("oldLoginName");

		if (accountManager.isLoginNameUnique(newLoginName, oldLoginName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}
	//-- 页面属性访问函数 --//
	public List<User> getAllUserList() {
		return allUserList;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public void setCheckedUserIds(List<String> checkedUserIds) {
		this.checkedUserIds = checkedUserIds;
	}

	public void setWorkingVersion(Integer workingVersion) {
		this.workingVersion = workingVersion;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public List<Role> getRolesAll() {
		return roleDao.getAll();
	}

	public List<String> getCheckedRoles() {
		return checkedRoles;
	}

	public void setCheckedRoles(List<String> roles) {
		this.checkedRoles = roles;
	}
	
	public List<Area> getAreasAll() {
		return areaDao.getAll();
	}
	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	public List<User> getAsmsAll() {
		return userDao.getUsersByRoleName("ASM");
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getAsm_id() {
		return asm_id;
	}

	public void setAsm_id(String asm_id) {
		this.asm_id = asm_id;
	}
}
