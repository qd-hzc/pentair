package com.pentair.showcase.rfq.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pentair.showcase.common.entity.IdEntity;

/**
 * RFQ角色对应关系表
 * @author Jiangshilin(36811928@qq.com)
 *
 */
@Entity
@Table(name = "RFQ_ROLE")
public class RfqRole extends IdEntity {
	String role;
	String editTemplate;
	String emailTemplate;
	RfqStatus status;

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEditTemplate() {
		return editTemplate;
	}
	public void setEditTemplate(String editTemplate) {
		this.editTemplate = editTemplate;
	}
	public String getEmailTemplate() {
		return emailTemplate;
	}
	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status")
	public RfqStatus getStatus() {
		return status;
	}
	public void setStatus(RfqStatus status) {
		this.status = status;
	}
}
