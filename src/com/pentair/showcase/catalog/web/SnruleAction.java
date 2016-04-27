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

import com.pentair.showcase.catalog.dao.SnRuleDao;
import com.pentair.showcase.catalog.entity.*;
import com.pentair.showcase.common.web.CrudActionSupport;

@Namespace("/catalog")
@InterceptorRefs( { @InterceptorRef("paramsPrepareParamsStack") })
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "snrule.action", type = "redirect"),
			@Result(name = "getone", location = "/WEB-INF/content/catalog/snrule-getone.jsp"),})
public class SnruleAction extends CrudActionSupport<SnRule> {

	private static final long serialVersionUID = 7245227394962925095L;
	
	private String id;
	private String time;
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private String name;
	private SnRule entity;
	private SnRuleDao snRuleDao;
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改
	private Page<SnRule> page = new Page<SnRule>(pageSize);

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
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("name");
			page.setOrder(Page.ASC);
		}

		page = snRuleDao.findPage(page, filters);
		return SUCCESS;
	}

	public Page<SnRule> getPage() {
		return page;
	}

	@Override
	public String input() throws Exception {
		if(time != null) {
			return "getone";
		}
		else {
			return INPUT;
		}
	}


	@Override
	public String save() throws Exception {
		if (id!=null&&!"".equals(id)&&workingVersion < entity.getVersion()) {
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
