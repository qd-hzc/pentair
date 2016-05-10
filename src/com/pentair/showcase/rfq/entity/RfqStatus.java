package com.pentair.showcase.rfq.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.pentair.showcase.common.entity.IdEntity;

/**
 * RFQ状态
 *
 * @author Jiangshilin(36811928@qq.com)
 */
@Entity
@Table(name = "RFQ_STATUS")
public class RfqStatus extends IdEntity {
    String name;
    List<RfqRole> rfqRoles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //与RfqRole的一对多关系
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    @OrderBy(value = "role")
    public List<RfqRole> getRfqRoles() {
        return rfqRoles;
    }

    public void setRfqRoles(List<RfqRole> rfqRoles) {
        this.rfqRoles = rfqRoles;
    }

}