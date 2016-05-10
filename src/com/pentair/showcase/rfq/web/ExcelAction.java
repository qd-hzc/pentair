package com.pentair.showcase.rfq.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.MatchType;

import com.pentair.showcase.common.web.CrudActionSupport;
import com.pentair.showcase.rfq.dao.RfqDetailDao;
import com.pentair.showcase.rfq.entity.Rfq;
import com.pentair.showcase.rfq.entity.RfqDetail;

@Namespace("/rfq")
@InterceptorRefs({@InterceptorRef("paramsPrepareParamsStack")})
@Results({@Result(name = CrudActionSupport.RELOAD, location = "productno.action", type = "redirect")})
public class ExcelAction extends CrudActionSupport<RfqDetail> {

    private static final long serialVersionUID = -776548819052326504L;

    @Autowired
    private RfqDetailDao rfqDetailDao;

    private Page<RfqDetail> page = new Page<RfqDetail>(Integer.MAX_VALUE);

    public RfqDetailDao getRfqDetailDao() {
        return rfqDetailDao;
    }

    public void setRfqDetailDao(RfqDetailDao rfqDetailDao) {
        this.rfqDetailDao = rfqDetailDao;
    }

    @Override
    public RfqDetail getModel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String list() throws Exception {
        List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
        PropertyFilter filter = new PropertyFilter("LIKES_productNo", "_________________");
        filters.add(filter);

        //设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("updateTime");
            page.setOrder(Page.DESC);
        }

        page = this.rfqDetailDao.findPage(page, filters);
        System.out.println(page.getTotalCount());
        return SUCCESS;
    }

    public Page<RfqDetail> getPage() {
        return page;
    }

    public void setPage(Page<RfqDetail> page) {
        this.page = page;
    }

    @Override
    public String input() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String save() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String delete() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void prepareModel() throws Exception {
        // TODO Auto-generated method stub

    }

}
