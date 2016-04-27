package com.pentair.showcase.rfq.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.MatchType;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.pentair.showcase.common.dao.UserDao;
import com.pentair.showcase.common.entity.User;
import com.pentair.showcase.common.web.CrudActionSupport;
import com.pentair.showcase.rfq.dao.RfqDao;
import com.pentair.showcase.rfq.dao.RfqRoleDao;
import com.pentair.showcase.rfq.entity.Rfq;
import com.pentair.showcase.rfq.entity.RfqDetail;
import com.pentair.showcase.security.LoginUser;

@Namespace("/rfq")
@InterceptorRefs( { @InterceptorRef("paramsPrepareParamsStack") })
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "productno.action", type = "redirect") })
public class ExcelrfqAction extends CrudActionSupport<RfqDetail> {

	private static final long serialVersionUID = -776548819052326504L;
	private LoginUser loginUser=(LoginUser)SpringSecurityUtils.getCurrentUser();
	private RfqRoleDao rfqRoleDao;
	private User user;
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	private RfqDao rfqDao;
	
	private Page<Rfq> page = new Page<Rfq>(Integer.MAX_VALUE);
	
	public RfqDao getRfqDao() {
		return rfqDao;
	}

	public void setRfqDao(RfqDao rfqDao) {
		this.rfqDao = rfqDao;
	}

	public RfqRoleDao getRfqRoleDao() {
		return rfqRoleDao;
	}

	public void setRfqRoleDao(RfqRoleDao rfqRoleDao) {
		this.rfqRoleDao = rfqRoleDao;
	}
	
	@Override
	public RfqDetail getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		user=userDao.get(loginUser.getId());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		
		if(!SpringSecurityUtils.hasAnyRole("ROLE_ADMIN")){//管理员不需增加此过滤条件
			PropertyFilter filter=null;
			Collection<String> c=rfqRoleDao.getListStatusByRoleName(user.getRoleShortNames());
			if(c!=null &&!c.isEmpty()){
				filter=new PropertyFilter("INS_status.id",c);
				
			}else{
				filter=new PropertyFilter("EQS_status.id","xxxxxxxx");//故意查不出
			}
			filters.add(filter);
		}

		if(SpringSecurityUtils.hasAnyRole("ROLE_MM")) {
			PropertyFilter filter=new PropertyFilter("EQS_series.brand.ownerMM.id",loginUser.getId());
			filters.add(filter);
		}

		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("inputTime");
			page.setOrder(Page.DESC);
		}

		page = this.rfqDao.findPage(page, filters);
		System.out.println(page.getTotalCount());
		return SUCCESS;
	}

	public Page<Rfq> getPage() {
		return page;
	}

	public void setPage(Page<Rfq> page) {
		this.page = page;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
