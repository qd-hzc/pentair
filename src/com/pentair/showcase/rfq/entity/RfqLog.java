package com.pentair.showcase.rfq.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.*;

import com.pentair.showcase.common.entity.IdEntity;

/**
 * RFQ操作日志表
 *
 * @author Jiangshilin(36811928@qq.com)
 */
@Entity
@Table(name = "RFQ_LOG")
public class RfqLog extends IdEntity {
    String fromUser;
    String fromRole;
    String toUser;
    String toRole;
    Date theDate;
    String info;
    private Rfq rfq;

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getFromRole() {
        return fromRole;
    }

    public void setFromRole(String fromRole) {
        this.fromRole = fromRole;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToRole() {
        return toRole;
    }

    public void setToRole(String toRole) {
        this.toRole = toRole;
    }

    public Date getTheDate() {
        return theDate;
    }

    public void setTheDate(Date theDate) {
        this.theDate = theDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
}
