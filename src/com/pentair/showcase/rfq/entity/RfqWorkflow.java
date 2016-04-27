package com.pentair.showcase.rfq.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pentair.showcase.common.entity.IdEntity;

/**
 * RFQ工作流表
 * @author Jiangshilin(36811928@qq.com)
 *
 */
@Entity
@Table(name = "RFQ_workflow")
public class RfqWorkflow extends IdEntity {
	String title;
	RfqStatus currentStatus;
	RfqStatus nextStatus;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "current_status")
	public RfqStatus getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(RfqStatus currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "next_status")
	public RfqStatus getNextStatus() {
		return nextStatus;
	}
	public void setNextStatus(RfqStatus nextStatus) {
		this.nextStatus = nextStatus;
	}
}
