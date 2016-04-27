package com.pentair.showcase.rfq.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.pentair.showcase.common.entity.IdEntity;
import com.pentair.showcase.catalog.entity.*;

/**
 * RFQ明细
 * @author Jiangshilin(36811928@qq.com)
 *
 */
@Entity
@Table(name = "RFQ_DETAIL")
public class RfqDetail extends IdEntity {
	private Rfq rfq;
	
	private Integer version;
	private String  sn;//四班号
	private String  productNo;
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	private String  productName;
	private String  competitorName;
	private String  competitorPrice;
	private String  expectedPrice;
	private String  agentProfit;
	private String  deliveryCycle;
	private String  opportunity;
	private String  quantityYear;
	private String  quantityMin;
	private String  note;
	private String  saleAmountYear;
	private String  snruleId;
	
	private Float materialCost;
	private Float loh;
	private Float toolingCharge;
	private Float batch;
	
	private String specialPrice;
	private String specialApprover;
	private String deliveryWeeks;

	public String getSnruleId() {
		return snruleId;
	}

	public void setSnruleId(String snruleId) {
		this.snruleId = snruleId;
	}

	public String getSaleAmountYear() {
		return saleAmountYear;
	}

	public void setSaleAmountYear(String saleAmountYear) {
		this.saleAmountYear = saleAmountYear;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCompetitorName() {
		return competitorName;
	}

	public void setCompetitorName(String competitorName) {
		this.competitorName = competitorName;
	}

	public String getCompetitorPrice() {
		return competitorPrice;
	}

	public void setCompetitorPrice(String competitorPrice) {
		this.competitorPrice = competitorPrice;
	}

	public String getExpectedPrice() {
		return expectedPrice;
	}

	public void setExpectedPrice(String expectedPrice) {
		this.expectedPrice = expectedPrice;
	}

	public String getAgentProfit() {
		return agentProfit;
	}

	public void setAgentProfit(String agentProfit) {
		this.agentProfit = agentProfit;
	}

	public String getDeliveryCycle() {
		return deliveryCycle;
	}

	public void setDeliveryCycle(String deliveryCycle) {
		this.deliveryCycle = deliveryCycle;
	}

	public String getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(String opportunity) {
		this.opportunity = opportunity;
	}

	public String getQuantityYear() {
		return quantityYear;
	}

	public void setQuantityYear(String quantityYear) {
		this.quantityYear = quantityYear;
	}

	public String getQuantityMin() {
		return quantityMin;
	}

	public void setQuantityMin(String quantityMin) {
		this.quantityMin = quantityMin;
	}

	@Version
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	//与RFQ的多对一关系
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rfq_id")
	public Rfq getRfq() {
		return rfq;
	}
	
	public void setRfq(Rfq rfq) {
		this.rfq = rfq;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getMaterialCost() {
		return materialCost;
	}

	public void setMaterialCost(Float materialCost) {
		this.materialCost = materialCost;
	}

	public Float getLoh() {
		return loh;
	}

	public void setLoh(Float loh) {
		this.loh = loh;
	}

	public Float getToolingCharge() {
		return toolingCharge;
	}

	public void setToolingCharge(Float toolingCharge) {
		this.toolingCharge = toolingCharge;
	}

	public Float getBatch() {
		return batch;
	}

	public void setBatch(Float batch) {
		this.batch = batch;
	}

	public String getDeliveryWeeks() {
		return deliveryWeeks;
	}

	public void setDeliveryWeeks(String deliveryWeeks) {
		this.deliveryWeeks = deliveryWeeks;
	}

	public String getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(String specialPrice) {
		this.specialPrice = specialPrice;
	}

	public String getSpecialApprover() {
		return specialApprover;
	}

	public void setSpecialApprover(String specialApprover) {
		this.specialApprover = specialApprover;
	}
	
	/*
	public String getSpecialClassName() {
		if(specialPrice == null || specialPrice.trim().length() == 0) {
			return "";
		} else {
			return "specialTr";
		}
	}
	public Float getTotalUnitCose() {
		if(this.materialCost != null && this.loh != null) {
			float cost = this.materialCost.floatValue() + this.loh.floatValue();
			return new Float(cost);
		}
		else {
			return null;
		}
	}
	*/
}
