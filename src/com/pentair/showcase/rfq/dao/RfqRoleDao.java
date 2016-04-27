package com.pentair.showcase.rfq.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.pentair.showcase.common.dao.RoleDao;
import com.pentair.showcase.common.entity.Role;
import com.pentair.showcase.common.entity.User;
import com.pentair.showcase.rfq.entity.RfqRole;
import com.pentair.showcase.rfq.entity.RfqStatus;

@Component
public class RfqRoleDao extends HibernateDao<RfqRole, String> {

	/**
	 * 根据角色名称获取可查询记录的状态列表
	 * @param role
	 * @return
	 */
	public Collection<String> getListStatusByRoleName(String role){		
		List<RfqRole> list=this.findBy("role", role);
		List<String> list2=new ArrayList<String>();
		for(RfqRole rfqRole:list){
			list2.add(rfqRole.getStatus().getId());
		}
		
		return list2;		
	}
	
	/**
	 * 根据角色名称获取可编辑记录的状态列表，用,分割，组合成字符串
	 * @param role
	 * @return
	 */
	public String getEditStatusByRoleName(String role){
		String str="";
		List<RfqRole> list=this.findBy("role", role);
		for(RfqRole rfqRole:list){
			if(rfqRole.getEditTemplate()!=null &&!"".equals(rfqRole.getEditTemplate())){
				str+=rfqRole.getStatus().getId()+",";
			}
		}		
		return str;		
	}
	
	/**
	 * 根据角色名称和RFQ状态获取对应的RfqRole对象
	 * @param role
	 * @param status
	 * @return
	 */
	public RfqRole getByRoleNameAndStatus(String role,RfqStatus status){
		return this.findUnique("FROM RfqRole WHERE role =? AND status.id = ?", role,status.getId());		
	}
	
}
