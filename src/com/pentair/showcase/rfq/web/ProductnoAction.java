package com.pentair.showcase.rfq.web;

import java.util.*;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.pentair.showcase.catalog.dao.SnRuleDao;
import com.pentair.showcase.catalog.entity.*;
import com.pentair.showcase.common.web.CrudActionSupport;

@Namespace("/rfq")
@InterceptorRefs({@InterceptorRef("paramsPrepareParamsStack")})
@Results({@Result(name = CrudActionSupport.RELOAD, location = "productno.action", type = "redirect")})
public class ProductnoAction extends CrudActionSupport<SnRule> {

    private static final long serialVersionUID = 777713123380255862L;
    private String id;
    private String name;
    private SnRule entity;
    private SnRuleDao snRuleDao;
    private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改
    private Page<SnRule> page = new Page<SnRule>(Integer.MAX_VALUE);
    private List<SnRule> snRulesAll;

    public List<SnRule> getSnRulesAll() {
        return snRulesAll;
    }

    public void setSnRuleAll(List<SnRule> snRulesAll) {
        this.snRulesAll = snRulesAll;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SnRule getEntity() {
        return entity;
    }

    public void setEntity(SnRule entity) {
        this.entity = entity;
    }

    public SnRuleDao getSnRuleDao() {
        return snRuleDao;
    }

    public void setSnRuleDao(SnRuleDao snRuleDao) {
        this.snRuleDao = snRuleDao;
    }

    public Integer getWorkingVersion() {
        return workingVersion;
    }

    public void setWorkingVersion(Integer workingVersion) {
        this.workingVersion = workingVersion;
    }

    @Override
    public SnRule getModel() {
        return entity;
    }

    @Override
    public String list() throws Exception {
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());

        List<String> prefixs = new ArrayList<String>();
        prefixs.add("IA");
        String prefix = Struts2Utils.getRequest().getParameter("prefix");
        if (prefix != null && prefix.length() > 0) {
            prefixs.add(prefix);
            if (prefix.equals("MH")) {
                prefixs.add("MI");
            }
        }

        PropertyFilter filter = new PropertyFilter("INS_d1", prefixs);
        filters.add(filter);

        //设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("name");
            page.setOrder(Page.ASC);
        }

        page = snRuleDao.findPage(page, filters);
        this.snRulesAll = page.getResult();
        return SUCCESS;
    }

    public Page<SnRule> getPage() {
        return page;
    }

    @Override
    public String input() throws Exception {
        return INPUT;
    }

    @Override
    public String save() throws Exception {
        if (id != null && !"".equals(id) && workingVersion < entity.getVersion()) {
            throw new StaleStateException("操作对象已被其他用户修改，请重新操作！");
        }

        snRuleDao.save(entity);

        addActionMessage("编码规则保存成功！");
        this.setPopup(true);//是弹出窗口
        return "result_success";
    }

    @Override
    public String delete() throws Exception {
        try {
            if (id != null) {
                snRuleDao.delete(id);
            }
        } catch (Exception e) {
            throw new StaleStateException("系统中有与该对象关联的数据，无法删除。");
        }
        return RELOAD;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (id != null && !id.equals("")) {
            entity = snRuleDao.get(id);
        } else {
            entity = new SnRule();
        }
    }

}
