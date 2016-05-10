package com.pentair.showcase.rfq.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.pentair.showcase.common.dao.RoleDao;
import com.pentair.showcase.common.entity.User;
import com.pentair.showcase.rfq.entity.Rfq;
import com.pentair.showcase.rfq.entity.RfqLog;

@Component
public class RfqLogDao extends HibernateDao<RfqLog, String> {

    @Transactional
    public void addRfqLog(Rfq rfq, User user) {
        RfqLog rfqLog = new RfqLog();
        rfqLog.setFromRole(user.getRoleNames());
        rfqLog.setFromUser(user.getName());
        rfqLog.setInfo(rfq.getStatus().getName());
        rfqLog.setTheDate(new Date());
        rfqLog.setRfq(rfq);
        this.save(rfqLog);
    }

}
