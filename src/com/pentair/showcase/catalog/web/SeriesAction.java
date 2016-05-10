package com.pentair.showcase.catalog.web;

import java.util.List;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.pentair.showcase.catalog.dao.BrandDao;
import com.pentair.showcase.catalog.dao.SeriesDao;
import com.pentair.showcase.catalog.entity.Brand;
import com.pentair.showcase.catalog.entity.Series;
import com.pentair.showcase.common.dao.UserDao;
import com.pentair.showcase.common.entity.User;
import com.pentair.showcase.common.web.CrudActionSupport;

@Namespace("/catalog")
@InterceptorRefs({@InterceptorRef("paramsPrepareParamsStack")})
@Results({@Result(name = CrudActionSupport.RELOAD, location = "series.action", type = "redirect"),
        @Result(name = "getone", location = "/WEB-INF/content/catalog/series-getone.jsp")})
public class SeriesAction extends CrudActionSupport<Series> {

    private static final long serialVersionUID = -3979024903324999013L;

    private String id;

    private String brand_id;

    private String pm_id;

    private String dm_id;

    private String ce_id;

    private Series entity;

    private SeriesDao seriesDao;

    private BrandDao brandDao;

    private UserDao userDao;

    private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改

    private Page<Series> page = new Page<Series>(pageSize);

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //-- ModelDriven 与 Preparable函数 --//
    public Series getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (id != null && !id.equals("")) {
            entity = seriesDao.get(id);
        } else {
            entity = new Series();
        }
    }

    //-- CRUD Action 函数 --//
    @Override
    public String list() throws Exception {
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());

        //设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("brand");
            page.setOrder(Page.ASC);
        }

        page = seriesDao.findPage(page, filters);
        return SUCCESS;
    }

    @Override
    public String input() throws Exception {
        if (entity.getBrand() != null) {
            this.setBrand_id(entity.getBrand().getId());
        }
        if (entity.getOwnerPM() != null) {
            this.setPm_id(entity.getOwnerPM().getId());
        }
        if (entity.getOwnerDM() != null) {
            this.setDm_id(entity.getOwnerDM().getId());
        }
        if (entity.getOwnerCE() != null) {
            this.setCe_id(entity.getOwnerCE().getId());
        }
        if (time != null) {
            return "getone";
        } else {
            return INPUT;
        }
    }

    @Override
    public String save() throws Exception {

        if (id != null && !"".equals(id) && workingVersion < entity.getVersion()) {
            throw new StaleStateException("操作对象已被其他用户修改，请重新操作！");
        }

        entity.setBrand(brandDao.get(brand_id));
        entity.setOwnerPM(userDao.get(pm_id));
        entity.setOwnerDM(userDao.get(dm_id));
        entity.setOwnerCE(userDao.get(ce_id));

        seriesDao.save(entity);

        addActionMessage("产品系列信息保存成功！");
        this.setPopup(true);//是弹出窗口
        return "result_success";
    }

    @Override
    public String delete() throws Exception {
        try {
            if (id != null) {
                seriesDao.delete(id);
            }
        } catch (Exception e) {
            throw new StaleStateException("系统中有与该对象关联的数据，无法删除。");
        }
        return RELOAD;
    }

    public Page<Series> getPage() {
        return page;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getPm_id() {
        return pm_id;
    }

    public void setPm_id(String pm_id) {
        this.pm_id = pm_id;
    }

    public String getDm_id() {
        return dm_id;
    }

    public void setDm_id(String dm_id) {
        this.dm_id = dm_id;
    }

    public String getCe_id() {
        return ce_id;
    }

    public void setCe_id(String ce_id) {
        this.ce_id = ce_id;
    }


    public void setBrandDao(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    public SeriesDao getSeriesDao() {
        return seriesDao;
    }

    public void setSeriesDao(SeriesDao seriesDao) {
        this.seriesDao = seriesDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setWorkingVersion(Integer workingVersion) {
        this.workingVersion = workingVersion;
    }

    public List<Brand> getBrandsAll() {
        return brandDao.getAll("name", true);
    }

    public Integer getWorkingVersion() {
        return workingVersion;
    }

    public List<User> getUsersAll() {
        return userDao.getAll("name", true);
    }


}