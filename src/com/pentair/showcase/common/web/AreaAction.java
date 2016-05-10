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
@InterceptorRefs({@InterceptorRef("paramsPrepareParamsStack")})
@Results({
        @Result(name = CrudActionSupport.RELOAD, location = "area.action", type = "redirect"),
})
public class AreaAction extends CrudActionSupport<Area> {

    /**
     *
     */
    private static final long serialVersionUID = 2262073956767407124L;

    private AccountManager accountManager;
    private AreaDao areaDao;
    private UserDao userDao;
    private BrandDao brandDao;

    private String id;
    private String brand_id;
    private String app_id;
    private String cs_id;

    private Area entity;
    private List<User> allUserList;
    private List<Area> allAreaList;
    private List<Brand> allBrandList;

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
        return allAreaList;
    }

    public List<Brand> getAllBrandList() {
        return brandDao.getAll();
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
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

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public void setCs_id(String cs_id) {
        this.cs_id = cs_id;
    }

    @Override
    public Area getModel() {
        return entity;
    }

    @Override
    public String list() throws Exception {
        try {
            allAreaList = areaDao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @Override
    public String input() throws Exception {

        return INPUT;
    }

    @Override
    public String save() throws Exception {
        //Brand brand = brandDao.get(brand_id);
        areaDao.save(entity);

        addActionMessage("地区信息保存成功！");
        this.setPopup(true);//是弹出窗口
        return "result_success";
    }

    @Override
    public String delete() throws Exception {
        return null;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (id != null && !id.equals("")) {
            entity = areaDao.get(id);
        }

        if (entity == null) {
            entity = new Area();
        }
    }

    public void setBrandDao(BrandDao brandDao) {
        this.brandDao = brandDao;
    }


}
