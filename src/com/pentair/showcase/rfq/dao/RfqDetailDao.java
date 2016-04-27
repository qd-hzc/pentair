package com.pentair.showcase.rfq.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;
import com.pentair.showcase.rfq.entity.RfqDetail;

@Component
public class RfqDetailDao extends HibernateDao<RfqDetail, String> {
}
