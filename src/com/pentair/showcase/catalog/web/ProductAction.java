package com.pentair.showcase.catalog.web;

import java.util.List;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.pentair.showcase.catalog.dao.ProductDao;
import com.pentair.showcase.catalog.dao.SeriesDao;
import com.pentair.showcase.catalog.entity.Product;
import com.pentair.showcase.catalog.entity.Series;
import com.pentair.showcase.common.web.CrudActionSupport;

@Namespace("/catalog")
@InterceptorRefs( { @InterceptorRef("paramsPrepareParamsStack") })
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "product.action", type = "redirect") })
public class ProductAction extends CrudActionSupport<Product> {

	private static final long serialVersionUID = 8663794528581090158L;

	private String id;
	
	private String series_id;

	private Product entity;
	
	private SeriesDao seriesDao;
	
	private ProductDao productDao;
	
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改
	
	private Page<Product> page = new Page<Product>(pageSize);
	

	//-- ModelDriven 与 Preparable函数 --//
	public Product getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null && !id.equals("")) {
			entity = productDao.get(id);
		} else {
			entity = new Product();
		}
	}
	
	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("name");
			page.setOrder(Page.ASC);
		}
		
		page = productDao.findPage(page, filters);
		return SUCCESS;
	}
	
	@Override
	public String input() throws Exception {
		if(entity.getSeries() != null){
			this.setSeries_id(entity.getSeries().getId());		
		}
		return INPUT;
	}

	public String getSeries_id() {
		return series_id;
	}

	public void setSeries_id(String series_id) {
		this.series_id = series_id;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override 
	public String save() throws Exception {
		
		if (id!=null&&!"".equals(id)&&workingVersion < entity.getVersion()) {
			throw new StaleStateException("操作对象已被其他用户修改，请重新操作！");
		}
		
		entity.setSeries(seriesDao.get(series_id));
		productDao.save(entity);
		
		addActionMessage("产品信息保存成功！");
		this.setPopup(true);//是弹出窗口
		return "result_success";
	}

	@Override
	public String delete() throws Exception {
		try {
			if (id != null) {
				productDao.delete(id);
			}
		} catch (Exception e) {
			throw new StaleStateException("系统中有与该对象关联的数据，无法删除。");
		}
		return RELOAD;
	}	
	
	public Page<Product> getPage() {
		return page;
	}
	
	public void setId(String id) {
		this.id = id;
	}


	public SeriesDao getSeriesDao() {
		return seriesDao;
	}

	public void setSeriesDao(SeriesDao seriesDao) {
		this.seriesDao = seriesDao;
	}

	public List<Series> getSeriesAll() {
		return seriesDao.getAll();
	}
	public Integer getWorkingVersion() {
		return workingVersion;
	}

	public void setWorkingVersion(Integer workingVersion) {
		this.workingVersion = workingVersion;
	}
}