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
import com.pentair.showcase.catalog.entity.Brand;
import com.pentair.showcase.common.dao.UserDao;
import com.pentair.showcase.common.entity.User;
import com.pentair.showcase.common.web.CrudActionSupport;

@Namespace("/catalog")
@InterceptorRefs({@InterceptorRef("paramsPrepareParamsStack")})
@Results({@Result(name = CrudActionSupport.RELOAD, location = "brand.action", type = "redirect")})
public class BrandAction extends CrudActionSupport<Brand> {
    private static final long serialVersionUID = 132709834406382026L;

    private String id;
    private String am_id;
    private String cm_id;
    private String mm_id;
    private String md_id;
    private String gm_id;
    private String csm_id;
    private Brand entity;

    private BrandDao brandDao;

    private UserDao userDao;

    private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改


    private Page<Brand> page = new Page<Brand>(pageSize);


    //-- ModelDriven 与 Preparable函数 --//
    public Brand getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (id != null && !id.equals("")) {
            entity = brandDao.get(id);
        } else {
            entity = new Brand();
        }
    }

    //-- CRUD Action 函数 --//
    @Override
    public String list() throws Exception {
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());

        //设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("name");
            page.setOrder(Page.ASC);
        }

        page = brandDao.findPage(page, filters);
        return SUCCESS;
    }

    @Override
    public String input() throws Exception {
        if (entity.getOwnerAM() != null) {
            this.setAm_id(entity.getOwnerAM().getId());
        }
        if (entity.getOwnerCM() != null) {
            this.setCm_id(entity.getOwnerCM().getId());
        }

        if (entity.getOwnerMM() != null) {
            this.setMm_id(entity.getOwnerMM().getId());
        }
        if (entity.getOwnerMD() != null) {
            this.setMd_id(entity.getOwnerMD().getId());
        }
        if (entity.getOwnerGM() != null) {
            this.setGm_id(entity.getOwnerGM().getId());
        }
        if (entity.getOwnerCSM() != null) {
            this.setCsm_id(entity.getOwnerCSM().getId());
        }
        return INPUT;
    }

    @Override
    public String save() throws Exception {

        if (id != null && !"".equals(id) && workingVersion < entity.getVersion()) {
            throw new StaleStateException("操作对象已被其他用户修改，请重新操作！");
        }

        entity.setOwnerAM(userDao.get(am_id));
        entity.setOwnerCM(userDao.get(cm_id));
        entity.setOwnerMM(userDao.get(mm_id));
        entity.setOwnerMD(userDao.get(md_id));
        entity.setOwnerGM(userDao.get(gm_id));
        entity.setOwnerCSM(userDao.get(csm_id));

        brandDao.save(entity);

        addActionMessage("品牌信息保存成功！");
        this.setPopup(true);//是弹出窗口
        return "result_success";
    }

    @Override
    public String delete() throws Exception {
        try {
            if (id != null) {
                brandDao.delete(id);
            }
        } catch (Exception e) {
            throw new StaleStateException("系统中有与该对象关联的数据，无法删除。");
        }
        return RELOAD;
    }

    public Page<Brand> getPage() {
        return page;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAm_id() {
        return am_id;
    }

    public void setAm_id(String am_id) {
        this.am_id = am_id;
    }

    public String getCm_id() {
        return cm_id;
    }

    public void setCm_id(String cm_id) {
        this.cm_id = cm_id;
    }


    public String getMm_id() {
        return mm_id;
    }

    public void setMm_id(String mm_id) {
        this.mm_id = mm_id;
    }

    public String getMd_id() {
        return md_id;
    }

    public void setMd_id(String md_id) {
        this.md_id = md_id;
    }

    public String getGm_id() {
        return gm_id;
    }

    public void setGm_id(String gm_id) {
        this.gm_id = gm_id;
    }

    public void setBrandDao(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsersAll() {
        return userDao.getAll("name", true);
    }

    public Integer getWorkingVersion() {
        return workingVersion;
    }

    public void setWorkingVersion(Integer workingVersion) {
        this.workingVersion = workingVersion;
    }

    public String getCsm_id() {
        return csm_id;
    }

    public void setCsm_id(String csm_id) {
        this.csm_id = csm_id;
    }

}
