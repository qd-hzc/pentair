package com.pentair.showcase.common.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.pentair.showcase.catalog.entity.Brand;
import com.pentair.showcase.common.entity.Area;
import com.pentair.showcase.common.entity.AreaApp;
import com.pentair.showcase.common.entity.Reply;
import com.pentair.showcase.common.entity.Subject;

@Component
public class AreaAppDao extends HibernateDao<AreaApp, String> {
    private static final String QUERY_WITH_DETAIL = "from AreaApp r fetch all properties where r.area=? and r.brand=?";

    /**
     * 获取回复内容.
     */
    public AreaApp getAreaApp(Area area, Brand brand) {
        return (AreaApp) findUnique(QUERY_WITH_DETAIL, area, brand);
    }

}
