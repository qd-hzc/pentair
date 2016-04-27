package com.pentair.showcase.rfq.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.pentair.showcase.rfq.entity.RfqRole;
import com.pentair.showcase.rfq.entity.RfqStatus;
import com.pentair.showcase.rfq.entity.RfqWorkflow;
import java.util.*;

@Component
public class RfqWorkflowDao extends HibernateDao<RfqWorkflow, String> {
	/**
	 * 根据角色名称和RFQ状态获取对应的RfqRole对象
	 * @param role
	 * @param status
	 * @return
	 */
	public List<RfqWorkflow> findNextSteps(RfqStatus status){
		return this.findBy("currentStatus.id", status.getId());		
	}
}
