package com.pentair.showcase.rfq.web;

import java.util.List;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter.MatchType;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.pentair.showcase.catalog.dao.SeriesDao;
import com.pentair.showcase.catalog.entity.Brand;
import com.pentair.showcase.catalog.entity.Series;
import com.pentair.showcase.common.dao.AreaAppDao;
import com.pentair.showcase.common.dao.FileUploadDao;
import com.pentair.showcase.common.dao.UserDao;
import com.pentair.showcase.common.entity.Area;
import com.pentair.showcase.common.entity.AreaApp;
import com.pentair.showcase.common.entity.FileUpload;
import com.pentair.showcase.common.entity.User;
import com.pentair.showcase.common.web.CrudActionSupport;
import com.pentair.showcase.rfq.dao.RfqDao;
import com.pentair.showcase.rfq.dao.RfqDetailDao;
import com.pentair.showcase.rfq.dao.RfqLogDao;
import com.pentair.showcase.rfq.dao.RfqStatusDao;
import com.pentair.showcase.rfq.entity.Rfq;
import com.pentair.showcase.rfq.entity.RfqDetail;
import com.pentair.showcase.security.LoginUser;
import com.pentair.utils.FileUtil;
import com.pentair.utils.MathUtil;
import com.pentair.utils.StringUtil;

@Namespace("/rfq")
@InterceptorRefs({@InterceptorRef("paramsPrepareParamsStack")})
@Results({
        @Result(name = CrudActionSupport.RELOAD, location = "rfqDetail.action", type = "redirect"),
        @Result(name = "edit_detail", location = "/WEB-INF/content/rfq/edit_detail.jsp"),
        @Result(name = "edit_detail_modify_productno", location = "/WEB-INF/content/rfq/edit_detail_modify_productno.jsp"),
        @Result(name = "view_detail", location = "/WEB-INF/content/rfq/view_detail.jsp"),
        @Result(name = "rfq_input", location = "rfq!input.action?id=${rfq_id}", type = "redirect"),
        @Result(name = "cs_input", location = "/WEB-INF/content/rfqcs/rfq_detail_input.jsp"),
        @Result(name = "input", location = "/WEB-INF/content/rfq/rfq-detail-input.jsp"),
})
public class RfqDetailAction extends CrudActionSupport<RfqDetail> {

    private static final long serialVersionUID = 5911063576392362675L;

    private String id;
    private RfqDetail entity;
    private String rfq_id;
    private String rfq_sn;
    private String rfqDetail_sn;
    private String status;
    private List<FileUpload> fileUploadsTech;
    private List<FileUpload> fileUploadsList;
    private User user;
    private LoginUser loginUser = (LoginUser) SpringSecurityUtils.getCurrentUser();
    private boolean salesCanModify;

    @Autowired
    private RfqDao rfqDao;
    @Autowired
    private RfqDetailDao rfqDetailDao;
    @Autowired
    private RfqStatusDao rfqStatusDao;
    @Autowired
    private RfqLogDao rfqLogDao;
    @Autowired
    private FileUploadDao fileUploadDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SeriesDao seriesDao;
    @Autowired
    private AreaAppDao areaAppDao;

    //-- ModelDriven 与 Preparable函数 --//
    public RfqDetail getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (id != null && !id.equals("")) {
            entity = rfqDetailDao.get(id);
        } else {
            entity = new RfqDetail();
        }
    }

    @Override
    public String list() throws Exception {
        return null;
    }

    @Override
    public String input() throws Exception {
        if (id != null && !"".endsWith(id)) {
            this.rfq_id = entity.getRfq().getId();
            this.rfq_sn = entity.getRfq().getSn();
        } else {
            Rfq rfq = rfqDao.get(rfq_id);
            this.rfq_id = rfq.getId();
            //生成四班号
            Series series = rfq.getSeries();
            series.setSn(series.getSn() + 1);
            String sn = series.getCode() + StringUtil.zeroPadString(series.getSn() + "", 4);
            entity.setSn(sn);
            this.rfq_sn = rfq.getSn();
            seriesDao.save(series);
        }

        Rfq rfq = entity.getRfq();
        if (rfq != null && rfq.getStatus().getId().equals("cs2sales")) {
            fileUploadsTech = fileUploadDao.findFileUploadByPackAndDirectoty(entity.getRfq().getSn(), entity.getSn(), "tech");
            fileUploadsList = fileUploadDao.findFileUploadByPackAndDirectoty(entity.getRfq().getSn(), entity.getSn(), "list");

            return "edit_detail_modify_productno";
        } else if (SpringSecurityUtils.hasAnyRole("ROLE_CS")) {
            return "cs_input";
        } else {
            return INPUT;
        }
    }

    @Override
    public String save() throws Exception {
        user = userDao.get(loginUser.getId());
        Rfq rfq = rfqDao.get(rfq_id);
        entity.setRfq(rfq);
        rfqDetailDao.save(entity);
        System.out.println("RfqDetailAction.save(): status=" + status);
        if ("input".equals(status)) {//保存，暂不提交
            addActionMessage("RFQ信息保存成功！");
            this.setPopup(true);//是弹出窗口
            return "result_success";
        } else if ("sales2am".equals(status)) {//保存并提交

            //如果是销售提交到应用工程部，自动跳过应用工程部经理，自动分配应用工程师
            if ("sales2am".equals(status)) {
                //2015-03-07, 修改为：销售首次提交时，就产生完整的产品编码。
                generateProductNoS10();

                //分配规则，根据地区属性中指定的应用工程师进行分配，如果没有，那么就交给应用工程部经理
                Area area = rfq.getOwnerSales().getArea();
                System.out.println("Edward: area=" + area.getName());
                Brand brand = rfq.getSeries().getBrand();
                System.out.println("Edward: brand=" + brand.getName());
                AreaApp areaApp = areaAppDao.getAreaApp(area, brand);
                System.out.println("Edward: areaApp=" + areaApp.getId());
                User app = areaApp.getOwnerApp();
                System.out.println("Edward: app=" + app.getName());
                if (app != null) {
                    rfq.setOwnerAPP(app);
                    status = "am2app";
                }
                //分配规则，如果找到，就取第一个进行指派，如果找不到，保持分给应用工程部经理不变。
//				List<User> users=userDao.getUsersByRoleNameAndArea("APP", rfq.getOwnerSales().getArea().getId());
//				if(users != null && users.size() > 0){
//					rfq.setOwnerAPP(users.get(0));
//					status = "am2app";
//				}
            }

            rfq.setStatus(rfqStatusDao.get(status));
            rfqDao.save(rfq);

            //记录RfqLog
            rfqLogDao.addRfqLog(rfq, user);

            //发送Email
            rfqDao.sendNormalNoticeMail(rfq);

            addActionMessage("RFQ信息已提交至应用工程部！");
            this.setPopup(true);//是弹出窗口
            return "result_success";
        } else if ("again".equals(status)) {//保存并继续新增
            entity = new RfqDetail();
            //生成四班号
            Series series = rfq.getSeries();
            series.setSn(series.getSn() + 1);
            this.rfqDetail_sn = series.getCode() + StringUtil.zeroPadString(series.getSn() + "", 4);
            seriesDao.save(series);
            return "edit_detail";
        } else if ("salesModifyBeforeDone".equals(status)) {
            return "view_detail";
        } else {//保存后返回
            System.out.println("RfqDetailAction.save(): before generate S10");
            //2015-07-20, 后续步骤中，其他人员如果修改了编号，直接生成后四位。
            generateProductNoS10();
            rfqDao.save(rfq);
            return "rfq_input";
        }
    }

    private void generateProductNoS10() {
        //生成产品编码的非标准编码部分（最后四位）
        List<RfqDetail> details = entity.getRfq().getRfqDetails();
        for (RfqDetail detail : details) {
            String productNo = detail.getProductNo();
            System.out.println("RfqDetailAction.generateProductNoS10(): "
                    + productNo + " " + productNo.length());
            if (productNo.length() >= 17) {
                continue;
            }
            List<RfqDetail> existDetails = this.rfqDetailDao.findBy("productNo", productNo + "%", MatchType.LIKE);
            int maxValue = 0;
            for (RfqDetail existDetail : existDetails) {
                String existProductNo = existDetail.getProductNo();
                int productNoLength = existProductNo.length();
                if (productNoLength >= 17) {
                    int value = Integer.parseInt(existProductNo.substring(productNoLength - 4));
                    if (value > maxValue) {
                        maxValue = value;
                    }
                }
            }
            maxValue++;
            if (maxValue < 10) {
                productNo += "000" + maxValue;
            } else if (maxValue < 100) {
                productNo += "00" + maxValue;
            } else if (maxValue < 1000) {
                productNo += "0" + maxValue;
            } else {
                productNo += maxValue;
            }
            System.out.println("RfqDetailAction.generateProductNoS10(): newProductNo=" + productNo);
            detail.setProductNo(productNo);
        }
    }

    @Override
    public String delete() throws Exception {
        try {
            if (id != null) {
                this.rfq_id = rfqDetailDao.get(id).getRfq().getId();
                rfqDetailDao.delete(id);
            }
        } catch (Exception e) {
            throw new StaleStateException("系统中有与该对象关联的数据，无法删除。");
        }
        return "rfq_input";
    }

    public String view() throws Exception {
        //entity=rfqDetailDao.get(id);
        if (SpringSecurityUtils.hasAnyRole("ROLE_SALES")) {
            String status = entity.getRfq().getStatus().getId();
            if (status.equals("ce2salesyes") || status.equals("asm2salesno")
                    || status.equals("pm2salesno2") || status.equals("md2salesno")) {
                this.salesCanModify = true;
                this.rfq_id = entity.getRfq().getId();
                this.rfq_sn = entity.getRfq().getSn();
            } else {
                this.salesCanModify = false;
            }
        } else {
            this.salesCanModify = false;
        }

        fileUploadsTech = fileUploadDao.findFileUploadByPackAndDirectoty(entity.getRfq().getSn(), entity.getSn(), "tech");
        fileUploadsList = fileUploadDao.findFileUploadByPackAndDirectoty(entity.getRfq().getSn(), entity.getSn(), "list");

        return "view_detail";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRfq_id() {
        return rfq_id;
    }

    public void setRfq_id(String rfq_id) {
        this.rfq_id = rfq_id;
    }

    public String getRfq_sn() {
        return rfq_sn;
    }

    public void setRfq_sn(String rfq_sn) {
        this.rfq_sn = rfq_sn;
    }

    public String getRfqDetail_sn() {
        return rfqDetail_sn;
    }

    public void setRfqDetail_sn(String rfqDetail_sn) {
        this.rfqDetail_sn = rfqDetail_sn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FileUpload> getFileUploadsTech() {
        return fileUploadsTech;
    }

    public void setFileUploadsTech(List<FileUpload> fileUploadsTech) {
        this.fileUploadsTech = fileUploadsTech;
    }

    public List<FileUpload> getFileUploadsList() {
        return fileUploadsList;
    }

    public void setFileUploadsList(List<FileUpload> fileUploadsList) {
        this.fileUploadsList = fileUploadsList;
    }

    public boolean isSalesCanModify() {
        return salesCanModify;
    }

    public void setSalesCanModify(boolean salesCanModify) {
        this.salesCanModify = salesCanModify;
    }


}
