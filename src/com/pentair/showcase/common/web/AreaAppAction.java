package com.pentair.showcase.common.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;

import com.pentair.showcase.catalog.dao.BrandDao;
import com.pentair.showcase.catalog.entity.Brand;
import com.pentair.showcase.common.dao.*;
import com.pentair.showcase.common.entity.*;
import com.pentair.showcase.common.service.AccountManager;

@Namespace("/system")
@InterceptorRefs( { @InterceptorRef("paramsPrepareParamsStack") })
@Results({
	@Result(name = CrudActionSupport.RELOAD, location = "areaapp.action", type = "redirect"),
})
public class AreaAppAction extends CrudActionSupport<AreaApp> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2262073956767407124L;

	private AreaDao areaDao;
	private AreaAppDao areaAppDao;
	private UserDao userDao;
	private BrandDao brandDao;
	
	private String id;
	private String area_id;
	private String brand_id;
	private String app_id;
	private String cs_id;
	
	private AreaApp entity;
//	private List<User> allUserList;
//	private List<Area> allAreaList;
//	private List<Brand> allBrandList;
	private List<AreaApp> allAreaAppList;
	
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改

	public String getApp_id() {
		return app_id;
	}
	
	public String getCs_id() {
		return cs_id;
	}
	
	public String getBrand_id() {
		return brand_id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public List<User> getAllUserList() {
		return userDao.getAll("name", true);
	}
	
	public List<Area> getAllAreaList() {
		return areaDao.getAll();
	}
	
	public List<Brand> getAllBrandList() {
		return brandDao.getAll();
	}
	
	public List<AreaApp> getAllAreaAppList() {
		return areaAppDao.getAll();
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	

	public AreaAppDao getAreaAppDao() {
		return areaAppDao;
	}

	public void setAreaAppDao(AreaAppDao areaAppDao) {
		this.areaAppDao = areaAppDao;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public void setCs_id(String cs_id) {
		this.cs_id = cs_id;
	}
	
	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public Integer getWorkingVersion() {
		return workingVersion;
	}

	public void setWorkingVersion(Integer workingVersion) {
		this.workingVersion = workingVersion;
	}

	@Override
	public AreaApp getModel() {
		return entity;
	}
	@Override
	public String list() throws Exception {
		try {
			allAreaAppList = areaAppDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Override
	public String input() throws Exception {
		if(entity.getArea() != null) {
			this.area_id = entity.getArea().getId();
		}
		if(entity.getBrand() != null) {
			this.brand_id = entity.getBrand().getId();
		}
		if(entity.getOwnerApp() != null) {
			this.app_id = entity.getOwnerApp().getId();
		}
		if(entity.getOwnerCs() != null) {
			this.cs_id = entity.getOwnerCs().getId();
		}
		return INPUT;
	}
	@Override
	public String save() throws Exception {
		if (id!=null&&!"".equals(id)&&workingVersion < entity.getVersion()) {
			throw new StaleStateException("操作对象已被其他用户修改，请重新操作！");
		}
			
		entity.setArea(areaDao.get(area_id));
		entity.setBrand(brandDao.get(brand_id));
		entity.setOwnerApp(userDao.get(app_id));
		entity.setOwnerCs(userDao.get(cs_id));
		
		areaAppDao.save(entity);
		
		addActionMessage("地区品牌责任人信息保存成功！");
		this.setPopup(true);//是弹出窗口

		return "result_success";
	}
	
	@Override
	public String delete() throws Exception {
		try {
			if (id != null) {
				areaAppDao.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new StaleStateException("系统中有与该对象关联的数据，无法删除。");
		}
		return RELOAD;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (id != null && !id.equals("")) {
			entity = areaAppDao.get(id);
		}
		
		if (entity == null){
			entity = new AreaApp();
		}
	}

	public void setBrandDao(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	
}
