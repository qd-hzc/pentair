package com.pentair.showcase.rfq.entity;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import com.google.common.collect.Lists;
import com.pentair.showcase.catalog.entity.Product;
import com.pentair.showcase.catalog.entity.Series;
import com.pentair.showcase.common.entity.*;

/**
 * RFQ主要信息
 *
 * @author Jiangshilin(36811928@qq.com)
 */
@Entity
@Table(name = "RFQ")
public class Rfq extends IdEntity {
    private String sn;//编码
    private String note;//
    private String noteApp;
    private String noteCe;
    private String noteCePublic;
    private String notePm;
    private String noteMm;
    private String noteMd;
    private String noteGm;
    private Date inputTime;//
    private Date updateTime;//
    private Date tofinishDate;//
    private RfqStatus status;//
    private Integer version;
    private Series series;//对应的产品系列
    private User ownerSales;//对应的Salse
    private User ownerAPP;//对应的APP
    private User ownerCE;//对应的CE
    private User ownerCS;//对应的CS
    private String customerName;//
    private String customerLinkman1;//
    private String customerPhone1;//
    private String customerLinkman2;//
    private String customerPhone2;//
    private String agentName;//
    private String agentLinkman;//
    private String agentPhone;//
    private String trade;//

    private boolean appCheck1;
    private boolean appCheck2;
    private boolean appCheck3;
    private String appCheck4;//

    private int csBackCount;

    private String noteSpecialPrice;
    private String noteFd;
    private String noteProfit;

    private List<RfqDetail> rfqDetails = Lists.newArrayList();//对应的RFQ明细列表
    private List<RfqLog> rfqLogs = Lists.newArrayList();//对应的RFQ日志列表

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTofinishDate() {
        return tofinishDate;
    }

    public void setTofinishDate(Date tofinishDate) {
        this.tofinishDate = tofinishDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status")
    public RfqStatus getStatus() {
        return status;
    }

    public void setStatus(RfqStatus status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLinkman1() {
        return customerLinkman1;
    }

    public void setCustomerLinkman1(String customerLinkman1) {
        this.customerLinkman1 = customerLinkman1;
    }

    public String getCustomerPhone1() {
        return customerPhone1;
    }

    public void setCustomerPhone1(String customerPhone1) {
        this.customerPhone1 = customerPhone1;
    }

    public String getCustomerLinkman2() {
        return customerLinkman2;
    }

    public void setCustomerLinkman2(String customerLinkman2) {
        this.customerLinkman2 = customerLinkman2;
    }

    public String getCustomerPhone2() {
        return customerPhone2;
    }

    public void setCustomerPhone2(String customerPhone2) {
        this.customerPhone2 = customerPhone2;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentLinkman() {
        return agentLinkman;
    }

    public void setAgentLinkman(String agentLinkman) {
        this.agentLinkman = agentLinkman;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    @Column(nullable = false, unique = true)
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id")
    public User getOwnerSales() {
        return ownerSales;
    }

    public void setOwnerSales(User ownerSales) {
        this.ownerSales = ownerSales;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id")
    public User getOwnerAPP() {
        return ownerAPP;
    }

    public void setOwnerAPP(User ownerAPP) {
        this.ownerAPP = ownerAPP;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ce_id")
    public User getOwnerCE() {
        return ownerCE;
    }

    public void setOwnerCE(User ownerCE) {
        this.ownerCE = ownerCE;
    }

    @OneToMany(mappedBy = "rfq", fetch = FetchType.LAZY)
    @OrderBy(value = "sn")
    public List<RfqDetail> getRfqDetails() {
        return rfqDetails;
    }

    public void setRfqDetails(List<RfqDetail> rfqDetails) {
        this.rfqDetails = rfqDetails;
    }

    @OneToMany(mappedBy = "rfq", fetch = FetchType.LAZY)
    @OrderBy(value = "theDate")
    public List<RfqLog> getRfqLogs() {
        return rfqLogs;
    }

    public void setRfqLogs(List<RfqLog> rfqLogs) {
        this.rfqLogs = rfqLogs;
    }

    //与产品系列的多对一关系
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "series_id")
    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }


    public boolean isAppCheck1() {
        return appCheck1;
    }

    public void setAppCheck1(boolean appCheck1) {
        this.appCheck1 = appCheck1;
    }

    public boolean isAppCheck2() {
        return appCheck2;
    }

    public void setAppCheck2(boolean appCheck2) {
        this.appCheck2 = appCheck2;
    }

    public boolean isAppCheck3() {
        return appCheck3;
    }

    public void setAppCheck3(boolean appCheck3) {
        this.appCheck3 = appCheck3;
    }

    public String getAppCheck4() {
        return appCheck4;
    }

    public void setAppCheck4(String appCheck4) {
        this.appCheck4 = appCheck4;
    }

    public String getNoteApp() {
        return noteApp;
    }

    public void setNoteApp(String noteApp) {
        this.noteApp = noteApp;
    }

    public String getNoteCe() {
        return noteCe;
    }

    public void setNoteCe(String noteCe) {
        this.noteCe = noteCe;
    }

    public String getNotePm() {
        return notePm;
    }

    public void setNotePm(String notePm) {
        this.notePm = notePm;
    }

    public String getNoteMm() {
        return noteMm;
    }

    public void setNoteMm(String noteMm) {
        this.noteMm = noteMm;
    }

    public String getNoteMd() {
        return noteMd;
    }

    public void setNoteMd(String noteMd) {
        this.noteMd = noteMd;
    }

    public String getNoteGm() {
        return noteGm;
    }

    public void setNoteGm(String noteGm) {
        this.noteGm = noteGm;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cs_id")
    public User getOwnerCS() {
        return ownerCS;
    }

    public void setOwnerCS(User ownerCS) {
        this.ownerCS = ownerCS;
    }

    public String getNoteCePublic() {
        return noteCePublic;
    }

    public void setNoteCePublic(String noteCePublic) {
        this.noteCePublic = noteCePublic;
    }

    public int getCsBackCount() {
        return csBackCount;
    }

    public void setCsBackCount(int csBackCount) {
        this.csBackCount = csBackCount;
    }

    public String getNoteSpecialPrice() {
        return noteSpecialPrice;
    }

    public void setNoteSpecialPrice(String noteSpecialPrice) {
        this.noteSpecialPrice = noteSpecialPrice;
    }

    public String getNoteFd() {
        return noteFd;
    }

    public void setNoteFd(String noteFd) {
        this.noteFd = noteFd;
    }

    public String getNoteProfit() {
        return noteProfit;
    }

    public void setNoteProfit(String noteProfit) {
        this.noteProfit = noteProfit;
    }
}
