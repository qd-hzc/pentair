package com.pentair.showcase.rfq.web;

import java.util.*;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.MatchType;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.pentair.showcase.catalog.dao.BrandDao;
import com.pentair.showcase.catalog.dao.ProductDao;
import com.pentair.showcase.catalog.dao.SeriesDao;
import com.pentair.showcase.catalog.entity.Brand;
import com.pentair.showcase.catalog.entity.Product;
import com.pentair.showcase.catalog.entity.Series;
import com.pentair.showcase.common.dao.AreaAppDao;
import com.pentair.showcase.common.dao.AreaDao;
import com.pentair.showcase.common.dao.FileUploadDao;
import com.pentair.showcase.common.dao.UserDao;
import com.pentair.showcase.common.entity.Area;
import com.pentair.showcase.common.entity.AreaApp;
import com.pentair.showcase.common.entity.FileUpload;
import com.pentair.showcase.common.entity.User;
import com.pentair.showcase.common.web.CrudActionSupport;
import com.pentair.showcase.common.web.WebException;
import com.pentair.showcase.rfq.dao.RfqDao;
import com.pentair.showcase.rfq.dao.RfqDetailDao;
import com.pentair.showcase.rfq.dao.RfqLogDao;
import com.pentair.showcase.rfq.dao.RfqRoleDao;
import com.pentair.showcase.rfq.dao.RfqStatusDao;
import com.pentair.showcase.rfq.dao.RfqWorkflowDao;
import com.pentair.showcase.rfq.entity.Rfq;
import com.pentair.showcase.rfq.entity.RfqDetail;
import com.pentair.showcase.rfq.entity.RfqLog;
import com.pentair.showcase.rfq.entity.RfqRole;
import com.pentair.showcase.rfq.entity.RfqStatus;
import com.pentair.showcase.rfq.entity.RfqWorkflow;
import com.pentair.showcase.security.LoginUser;
import com.pentair.utils.DateUtil;
import com.pentair.utils.StringUtil;

@Namespace("/rfq")
@InterceptorRefs( { @InterceptorRef("paramsPrepareParamsStack") })
@Results({
	@Result(name = CrudActionSupport.RELOAD, location = "rfq.action", type = "redirect"),
	@Result(name = "edit_detail", location = "/WEB-INF/content/rfq/edit_detail.jsp"),
	@Result(name = "edit_input", location = "/WEB-INF/content/rfq/edit_input.jsp"),
	@Result(name = "edit_am", location = "/WEB-INF/content/rfq/edit_am.jsp"),
	@Result(name = "edit_app", location = "/WEB-INF/content/rfq/edit_app.jsp"),
	@Result(name = "edit_pm", location = "/WEB-INF/content/rfq/edit_pm.jsp"),
	@Result(name = "edit_mm", location = "/WEB-INF/content/rfq/edit_mm.jsp"),
	@Result(name = "edit_fd", location = "/WEB-INF/content/rfq/edit_fd.jsp"),
	@Result(name = "edit_md", location = "/WEB-INF/content/rfq/edit_md.jsp"),
	@Result(name = "edit_asm", location = "/WEB-INF/content/rfq/edit_asm.jsp"),
	@Result(name = "edit_gm", location = "/WEB-INF/content/rfq/edit_gm.jsp"),
	@Result(name = "edit_cm", location = "/WEB-INF/content/rfq/edit_cm.jsp"),
	@Result(name = "edit_ce", location = "/WEB-INF/content/rfq/edit_ce.jsp"),
	@Result(name = "edit_csm", location = "/WEB-INF/content/rfq/edit_csm.jsp"),
	@Result(name = "edit_sales", location = "/WEB-INF/content/rfq/edit_sales.jsp"),
	@Result(name = "edit_sales_spec", location = "/WEB-INF/content/rfq/edit_sales_spec.jsp"),
	@Result(name = "edit_pm_spec", location = "/WEB-INF/content/rfq/edit_pm_spec.jsp"),
	@Result(name = "view_rfq", location = "/WEB-INF/content/rfq/view_rfq.jsp"),
	@Result(name = "view_log", location = "/WEB-INF/content/rfq/view_log.jsp"),
	@Result(name = "cs_input", location = "/WEB-INF/content/rfqcs/rfq_input.jsp"),
	@Result(name = "edit_cs", location = "/WEB-INF/content/rfqcs/edit_cs.jsp"),
	@Result(name = "edit_cs_detail", location = "/WEB-INF/content/rfqcs/edit_detail.jsp"),
	@Result(name = "edit_sales_modify_productno", location = "/WEB-INF/content/rfq/edit_sales_modify_productno.jsp"),
})
public class RfqAction extends CrudActionSupport<Rfq> {

	private static final long serialVersionUID = -1550110826418002543L;

	private String id;
	
	private String rfq_id;
	
	private String rfq_sn;
	
	private String rfqDetail_sn;
	
	private String series_id;
	
	private String brand_id;
	
	private String product_id;
	
	private String app_id;
	
	private String ce_id;
	
	private String cs_id;

	private String rfqStatus;

	private String editStatus;
	
	private List<String> specialPrice;

	private Rfq entity;
	
	private RfqRole rfqRole;

	private User user;
	
	private UserDao userDao;
	
	private BrandDao brandDao;
	
	private SeriesDao seriesDao;
	
	private ProductDao productDao;	
	
	private RfqDao rfqDao;
	
	private RfqStatusDao rfqStatusDao;
	
	private RfqRoleDao rfqRoleDao;
	
	private RfqLogDao rfqLogDao;

	private RfqWorkflowDao rfqWorkflowDao;
	
	private RfqDetailDao rfqDetailDao;
	
	private AreaAppDao areaAppDao;
	
	private static List<String> specialPriceStatuses;
	private static Map<String, String> specialApprovers;
	static {
		specialPriceStatuses = new ArrayList<String>(6);
		specialPriceStatuses.add("asm2salesyes");
		specialPriceStatuses.add("asm2salesno");
		specialPriceStatuses.add("pm2salesyes2");
		specialPriceStatuses.add("pm2salesno2");
		specialPriceStatuses.add("fd2salesyes");
		specialPriceStatuses.add("fd2salesno");
		specialPriceStatuses.add("md2salesyes");
		specialPriceStatuses.add("md2salesno");
		
		specialApprovers = new HashMap<String, String>();
		specialApprovers.put("asm2salesyes", "区域销售经理批准");
		specialApprovers.put("asm2salesno", "区域销售经理否决");
		specialApprovers.put("pm2salesyes2", "产品经理批准");
		specialApprovers.put("pm2salesno2", "产品经理否决");
		specialApprovers.put("fd2salesyes", "财务部经理批准");
		specialApprovers.put("fd2salesno", "财务部经理否决");
		specialApprovers.put("md2salesyes", "销售总监批准");
		specialApprovers.put("md2salesno", "销售总监否决");
	}
	
	public RfqDetailDao getRfqDetailDao() {
		return rfqDetailDao;
	}

	public void setRfqDetailDao(RfqDetailDao rfqDetailDao) {
		this.rfqDetailDao = rfqDetailDao;
	}

	
	public AreaAppDao getAreaAppDao() {
		return areaAppDao;
	}

	public void setAreaAppDao(AreaAppDao areaAppDao) {
		this.areaAppDao = areaAppDao;
	}


	private FileUploadDao fileUploadDao;
	
	private AreaDao areaDao;	

	private List<RfqDetail> rfqDetails;
	
	private List<RfqLog> rfqLogs;
	
	private List<RfqWorkflow> rfqWorkflows;
	
	private List<FileUpload> fileUploadsCe;

	private List<FileUpload> fileUploadsCePublic;

	private List<FileUpload> fileUploadsPm;
	
	private List<FileUpload> fileUploadsSpecialPrice;

	private Page<Rfq> page = new Page<Rfq>(pageSize);
	
	private LoginUser loginUser=(LoginUser)SpringSecurityUtils.getCurrentUser();
	
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改
	

	//-- ModelDriven 与 Preparable函数 --//
	public Rfq getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null && !id.equals("")) {
			entity = rfqDao.get(id);
		} else {
			entity = new Rfq();
		}
	}
	
	//-- CRUD Action 函数 --//	
	@Override
	public String list() throws Exception {
		user=userDao.get(loginUser.getId());

		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());

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
		
		if(SpringSecurityUtils.hasAnyRole("ROLE_SALES")){
			PropertyFilter filter=new PropertyFilter("EQS_ownerSales.id",loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_AM")){
			PropertyFilter filter=new PropertyFilter("EQS_series.brand.ownerAM.id",loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_APP")){
			PropertyFilter filter=new PropertyFilter("EQS_ownerAPP.id",loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_CM")){
			PropertyFilter filter=new PropertyFilter("EQS_series.brand.ownerCM.id",loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_CE")){
			PropertyFilter filter=new PropertyFilter("EQS_ownerCE.id",loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_PM")){
			PropertyFilter filter=new PropertyFilter("EQS_series.ownerPM.id",loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_MM")){
			PropertyFilter filter=new PropertyFilter("EQS_series.brand.ownerMM.id",loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_MD")){
			PropertyFilter filter=new PropertyFilter("EQS_series.brand.ownerMD.id",loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_GM")){
			PropertyFilter filter=new PropertyFilter("EQS_series.brand.ownerGM.id",loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_DM")){
			//只要权限是设计工程部经理，就可以看到全部的，不需要再根据所负责的产品系列进行过滤。
//			PropertyFilter filter=new PropertyFilter("EQS_series.ownerDM.id",loginUser.getId());
//			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_CSM")){
			PropertyFilter filter=new PropertyFilter("EQS_series.brand.ownerCSM.id",loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_CS")){
			PropertyFilter filter=new PropertyFilter("EQS_ownerCS.id", loginUser.getId());
			filters.add(filter);
		}else if(SpringSecurityUtils.hasAnyRole("ROLE_ASM")){
			PropertyFilter filter=new PropertyFilter("EQS_ownerSales.asm.id", loginUser.getId());
			filters.add(filter);
		}
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("updateTime");
			page.setOrder(Page.DESC);
		}

		page = rfqDao.findPage(page, filters);
		
		this.editStatus=rfqRoleDao.getEditStatusByRoleName(user.getRoleShortNames());//用于判断是否显示编辑按钮

		return SUCCESS;
	}
	
	@Override
	public String input() throws Exception {		
		user=userDao.get(loginUser.getId());
		
		if(id!=null && !"".equals(id)){//编辑
			if(entity.getSeries() != null){
				this.series_id=entity.getSeries().getId();
				this.brand_id=entity.getSeries().getBrand().getId();
			}

			RfqStatus status=entity.getStatus();
			
			//如果是Sales提出特价申请，先修改Sales的状态
			if(("3rd2salesyes".equals(status.getId()) || "pm2salesyes".equals(status.getId()) || "ce2salesyes".equals(status.getId()))
					&& "specinput".equals(rfqStatus)){
				entity.setStatus(rfqStatusDao.get("specinput"));
				rfqDao.save(entity);
			}
			
			rfqRole=rfqRoleDao.getByRoleNameAndStatus(user.getRoleShortNames(), entity.getStatus());
			if(rfqRole==null ||rfqRole.getEditTemplate()==null ||"".equals(rfqRole.getEditTemplate())){
				throw new WebException("非法操作，没有编辑该RFQ的权限！");
			}else{
				rfqDetails=entity.getRfqDetails();//关联的产品信息
				
				rfqWorkflows=rfqWorkflowDao.findNextSteps(entity.getStatus());//下一步操作按钮
				
				fileUploadsCe=fileUploadDao.findFileUploadByPackAndDirectoty(entity.getSn(), "ce","");				
				fileUploadsCePublic=fileUploadDao.findFileUploadByPackAndDirectoty(entity.getSn(), "cePublic","");				
				fileUploadsPm=fileUploadDao.findFileUploadByPackAndDirectoty(entity.getSn(), "pm","");
				fileUploadsSpecialPrice=fileUploadDao.findFileUploadByPackAndDirectoty(entity.getSn(), "specialPrice","");
				
				return rfqRole.getEditTemplate();//根据RfqRole记录跳转不同的页面
			}			
		}
		else{//新增
			if(SpringSecurityUtils.hasAnyRole("ROLE_CS") || SpringSecurityUtils.hasAnyRole("ROLE_CSM")) {
				return "cs_input";
			}
			else if(!SpringSecurityUtils.hasAnyRole("ROLE_SALES")){
				throw new WebException("只有销售人员才能新增RFQ！");
			}
			return INPUT;
		}		
	}

	@Override 
	public String save() throws Exception {
		user=userDao.get(loginUser.getId());

		if (series_id!=null&&!"".equals(series_id)){
			entity.setSeries(seriesDao.get(series_id));
		}
		if(id==null || "".equals(id)) {//新增
			String sn=entity.getSeries().getOwnerPM().getShortName();
			sn+=user.getArea().getId()+DateUtil.format(new Date(), "yyyyMMddHHmmss");
			entity.setSn(sn);
			entity.setInputTime(new Date());
			if(SpringSecurityUtils.hasAnyRole("ROLE_CS") || SpringSecurityUtils.hasAnyRole("ROLE_CSM")) {
				entity.setOwnerCS(user);
				entity.setStatus(rfqStatusDao.get("csinput"));
			}
			else {
				entity.setOwnerSales(user);
				entity.setStatus(rfqStatusDao.get("input"));
			}
		
		}else{//更新
			//如果状态变为“已下单”，则自动分配给CS
			if("done".equals(rfqStatus)&&!"done".equals(entity.getStatus().getId())){
				generateProductNoS10();
				
				//分配规则，根据地区属性中指定的客户服务专员进行分配，如果没有，那么就交给客户服务部经理
				Area area = entity.getOwnerSales().getArea();
				Brand brand = entity.getSeries().getBrand();
				AreaApp areaApp = areaAppDao.getAreaApp(area, brand);
				User cs = areaApp.getOwnerCs();
				if(cs != null) {
					entity.setOwnerCS(cs);
				}
//				List<User> users=userDao.getUsersByRoleNameAndArea("CS", entity.getOwnerSales().getArea().getId());
//				if(users!=null&&users.size()==1){//只有一个区域对应一个CS时才自动分配，否则由CS经理手工分配
//					entity.setOwnerCS(users.get(0));
//				}
			}
			
			//如果是销售提交到应用工程部，自动跳过应用工程部经理，自动分配应用工程师
			if("sales2am".equals(rfqStatus)) {
				//2015-03-07, 修改为：销售首次提交时，就产生完整的产品编码。
				generateProductNoS10();
				
				//分配规则，根据地区和品牌负责人属性中指定的应用工程师进行分配，如果没有，那么就交给应用工程部经理
				Area area = entity.getOwnerSales().getArea();
				Brand brand = entity.getSeries().getBrand();
				AreaApp areaApp = areaAppDao.getAreaApp(area, brand);
				User app = areaApp.getOwnerApp();
				if(app != null) {
					entity.setOwnerAPP(app);
					rfqStatus = "am2app";
				}
				//分配规则，如果找到，就取第一个进行指派，如果找不到，保持分给应用工程部经理不变。
//				List<User> users=userDao.getUsersByRoleNameAndArea("APP", entity.getOwnerSales().getArea().getId());
//				if(users != null && users.size() > 0){
//					entity.setOwnerAPP(users.get(0));
//					rfqStatus = "am2app";
//				}
			}
			
			//如果是报价工程师提交到报价工程部，自动跳过报价工程部经理，自动分配报价工程师
			if("pm2cm".equals(rfqStatus)) {
				//如果rfq中已经有指定的报价工程师，则不需要重新自动指定
				if(entity.getOwnerCE() != null) {
					rfqStatus = "cm2ce";
				}
				else {
					// 分配规则，每个产品系列指定一个负责的报价工程师，如果找不到，保持分给应用工程部经理不变。
					User ce = entity.getSeries().getOwnerCE();
					if (ce != null) {
						entity.setOwnerCE(ce);
						rfqStatus = "cm2ce";
					}
				}
			}
			
			if("specialprice2asm".equals(rfqStatus) || "asm2salesyes".equals(rfqStatus) || "asm2pm".equals(rfqStatus)) {
				List<RfqDetail> rfqDetails = entity.getRfqDetails();
				for(int i = 0; i < rfqDetails.size(); i ++) {
					RfqDetail rfqDetail = rfqDetails.get(i);
					rfqDetail.setSpecialPrice(specialPrice.get(i));
					rfqDetail.setSpecialApprover("");
				}
			}
			
			if(specialPriceStatuses.contains(rfqStatus)) {
				List<RfqDetail> rfqDetails = entity.getRfqDetails();
				for(int i = 0; i < rfqDetails.size(); i ++) {
					RfqDetail rfqDetail = rfqDetails.get(i);
					String specialPrice = rfqDetail.getSpecialPrice();
					if(specialPrice != null && specialPrice.length() > 0) {
						rfqDetail.setSpecialApprover(specialApprovers.get(rfqStatus));
					}
				}
			}
			
			//对于重新修改客户信息，清除特价信息
			if("input2".equals(rfqStatus)) {
				List<RfqDetail> rfqDetails = entity.getRfqDetails();
				for(int i = 0; i < rfqDetails.size(); i ++) {
					RfqDetail rfqDetail = rfqDetails.get(i);
					rfqDetail.setSpecialPrice("");
					rfqDetail.setSpecialApprover("");
				}
			}
			
			if(SpringSecurityUtils.hasAnyRole("ROLE_CS") || SpringSecurityUtils.hasAnyRole("ROLE_CSM")) {
				generateProductNoS10();
			}
			if (app_id!=null && !"".equals(app_id) && rfqStatus.equals("am2app")){
				entity.setOwnerAPP(userDao.get(app_id));
			}
			if (ce_id!=null&&!"".equals(ce_id) && rfqStatus.equals("cm2ce")){
				entity.setOwnerCE(userDao.get(ce_id));
			}
			if (cs_id!=null&&!"".equals(cs_id)){
				entity.setOwnerCS(userDao.get(cs_id));
			}
			
			if(entity.getStatus().getId().equals("cs2sales") && rfqStatus.equals("done")) {
				entity.setCsBackCount(entity.getCsBackCount() + 1);
			}
			
			entity.setStatus(rfqStatusDao.get(rfqStatus));
		}
		
		
		entity.setUpdateTime(new Date());
		rfqDao.save(entity);
		
		this.rfq_id=entity.getId();
		this.rfq_sn=entity.getSn();
		
		//记录RfqLog		
		rfqLogDao.addRfqLog(entity, user);
		
		//发送Email		
		rfqDao.sendNormalNoticeMail(entity);
		
		if(id==null || "".equals(id)) {//新增，转至RfqDetail编辑页面
			//生成四班号
			Series series=entity.getSeries();
			series.setSn(series.getSn()+1);
			this.rfqDetail_sn=series.getCode()+StringUtil.zeroPadString(series.getSn()+"", 4);
			seriesDao.save(series);
			if(SpringSecurityUtils.hasAnyRole("ROLE_CS") || SpringSecurityUtils.hasAnyRole("ROLE_CSM")) {
				return "edit_cs_detail";
			}
			else {
				return "edit_detail";
			}
		}else{
			addActionMessage("RFQ已提交，编号："+entity.getSn()+"，状态："+entity.getStatus().getName());
			this.setPopup(true);//是弹出窗口
			return "result_success"; 
		}
	}
	
	private void generateProductNoS10() {
		//生成产品编码的非标准编码部分（最后四位）
		List<RfqDetail> details = entity.getRfqDetails();
		for(RfqDetail detail : details) {
			String productNo = detail.getProductNo();
			//System.out.println(productNo + " " + productNo.length());
			if(productNo.length() >= 17) {
				continue;
			}
			List<RfqDetail> existDetails = this.rfqDetailDao.findBy("productNo", productNo + "%", MatchType.LIKE);
			int maxValue = 0;
			for(RfqDetail existDetail : existDetails) {
				String existProductNo = existDetail.getProductNo();
				int productNoLength = existProductNo.length();
				if(productNoLength >= 17) {
					int value = Integer.parseInt(existProductNo.substring(productNoLength - 4));
					if(value > maxValue) {
						maxValue = value;
					}
				}
			}
			maxValue++;
			if(maxValue < 10) {
				productNo += "000" + maxValue;
			}
			else if(maxValue < 100) {
				productNo += "00" + maxValue;
			}
			else if(maxValue < 1000) {
				productNo += "0" + maxValue;
			}
			else {
				productNo += maxValue;
			}
			detail.setProductNo(productNo);
		}
	}
	
	public String view() throws Exception {
		fileUploadsCe=fileUploadDao.findFileUploadByPackAndDirectoty(entity.getSn(), "ce","");
		fileUploadsCePublic=fileUploadDao.findFileUploadByPackAndDirectoty(entity.getSn(), "cePublic","");
		fileUploadsPm=fileUploadDao.findFileUploadByPackAndDirectoty(entity.getSn(), "pm","");
		fileUploadsSpecialPrice=fileUploadDao.findFileUploadByPackAndDirectoty(entity.getSn(), "specialPrice","");

		return "view_rfq";
	}
	
	public String viewLog() throws Exception {
		entity=rfqDao.get(id);
		rfqLogs=entity.getRfqLogs();
		return "view_log";
	}
	
	@Override
	public String delete() throws Exception {
		return RELOAD;
	}	
	
	public RfqDao getRfqDao() {
		return rfqDao;
	}
	
	public void setRfqDao(RfqDao rfqDao) {
		this.rfqDao = rfqDao;
	}

	public Page<Rfq> getPage() {
		return page;
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
	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public String getSeries_id() {
		return series_id;
	}

	public void setSeries_id(String series_id) {
		this.series_id = series_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getCe_id() {
		return ce_id;
	}

	public void setCe_id(String ce_id) {
		this.ce_id = ce_id;
	}
	
	public SeriesDao getSeriesDao() {
		return seriesDao;
	}

	public void setSeriesDao(SeriesDao seriesDao) {
		this.seriesDao = seriesDao;
	}

	public List<Series> getSeriesAll() {
		if(brand_id!=null && !"".equals(brand_id)){
			return seriesDao.findBy("brand.id", brand_id,"name", true);
		}else{
			return seriesDao.getAll("name", true);
		}
	}
	
	public List<Product> getProductsAll() {		
		if(series_id!=null && !"".equals(series_id)){
			return productDao.findBy("series.id", series_id,"name", true);
		}else{
			return productDao.getAll("name", true);
		}
	}
	
	public List<Area> getAreasAll() {		
		return areaDao.getAll("name", true);
	}
	
	public Integer getWorkingVersion() {
		return workingVersion;
	}

	public BrandDao getBrandDao() {
		return brandDao;
	}

	public void setBrandDao(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	
	public RfqRoleDao getRfqRoleDao() {
		return rfqRoleDao;
	}

	public void setRfqRoleDao(RfqRoleDao rfqRoleDao) {
		this.rfqRoleDao = rfqRoleDao;
	}
	public RfqStatusDao getRfqStatusDao() {
		return rfqStatusDao;
	}

	public void setRfqStatusDao(RfqStatusDao rfqStatusDao) {
		this.rfqStatusDao = rfqStatusDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setWorkingVersion(Integer workingVersion) {
		this.workingVersion = workingVersion;
	}
	public List<Brand> getBrandsAll() {
		return brandDao.getAll("name", true);
	}

	public List<User> getAPPUsersAll() {
		List<User> users=userDao.getUsersByRoleName("APP");
		users.add(brandDao.get(this.brand_id).getOwnerAM());
		
		return users;
	}
	
	public List<User> getCEUsersAll() {
		List<User> users=userDao.getUsersByRoleName("CE");
		users.add(brandDao.get(this.brand_id).getOwnerCM());
		
		return users;
	}
	
	public List<User> getCSUsersAll() {
		List<User> users=userDao.getUsersByRoleName("CS");
		
		return users;
	}
	
	public List<User> getSalesUsersAll() {
		List<User> users=userDao.getUsersByRoleName("SALES");
		
		return users;
	}
	
	public List<RfqStatus> getRfqStatusAll() {
		List<RfqStatus> status=rfqStatusDao.getAll("name", true);
		
		return status;
	}
	
	public String getEditStatus() {
		return editStatus;
	}

	public void setEditStatus(String editStatus) {
		this.editStatus = editStatus;
	}

	public RfqRole getRfqRole() {
		return rfqRole;
	}

	public void setRfqRole(RfqRole rfqRole) {
		this.rfqRole = rfqRole;
	}
	
	public List<RfqDetail> getRfqDetails() {
		return rfqDetails;
	}
	
	public RfqWorkflowDao getRfqWorkflowDao() {
		return rfqWorkflowDao;
	}

	public void setRfqWorkflowDao(RfqWorkflowDao rfqWorkflowDao) {
		this.rfqWorkflowDao = rfqWorkflowDao;
	}

	public List<RfqWorkflow> getRfqWorkflows() {
		return rfqWorkflows;
	}

	public void setRfqWorkflows(List<RfqWorkflow> rfqWorkflows) {
		this.rfqWorkflows = rfqWorkflows;
	}

	public void setRfqDetails(List<RfqDetail> rfqDetails) {
		this.rfqDetails = rfqDetails;
	}
	
	public String getRfqStatus() {
		return rfqStatus;
	}

	public void setRfqStatus(String rfqStatus) {
		this.rfqStatus = rfqStatus;
	}

	
	public FileUploadDao getFileUploadDao() {
		return fileUploadDao;
	}

	public void setFileUploadDao(FileUploadDao fileUploadDao) {
		this.fileUploadDao = fileUploadDao;
	}
	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}
	
	public RfqLogDao getRfqLogDao() {
		return rfqLogDao;
	}

	public void setRfqLogDao(RfqLogDao rfqLogDao) {
		this.rfqLogDao = rfqLogDao;
	}
	
	public List<RfqLog> getRfqLogs() {
		return rfqLogs;
	}

	public void setRfqLogs(List<RfqLog> rfqLogs) {
		this.rfqLogs = rfqLogs;
	}

	
	public List<FileUpload> getFileUploadsCe() {
		return fileUploadsCe;
	}

	public void setFileUploadsCe(List<FileUpload> fileUploadsCe) {
		this.fileUploadsCe = fileUploadsCe;
	}

	
	public List<FileUpload> getFileUploadsCePublic() {
		return fileUploadsCePublic;
	}

	public void setFileUploadsCePublic(List<FileUpload> fileUploadsCePublic) {
		this.fileUploadsCePublic = fileUploadsCePublic;
	}

	public List<FileUpload> getFileUploadsPm() {
		return fileUploadsPm;
	}

	public void setFileUploadsPm(List<FileUpload> fileUploadsPm) {
		this.fileUploadsPm = fileUploadsPm;
	}

	public String getCs_id() {
		return cs_id;
	}

	public void setCs_id(String cs_id) {
		this.cs_id = cs_id;
	}

	public List<FileUpload> getFileUploadsSpecialPrice() {
		return fileUploadsSpecialPrice;
	}

	public void setFileUploadsSpecialPrice(List<FileUpload> fileUploadsSpecialPrice) {
		this.fileUploadsSpecialPrice = fileUploadsSpecialPrice;
	}

	public List<String> getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(List<String> specialPrice) {
		this.specialPrice = specialPrice;
	}
}