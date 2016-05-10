package com.pentair.showcase.schedule;

import java.util.Date;
import java.util.List;

import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.pentair.showcase.rfq.dao.RfqDao;
import com.pentair.showcase.rfq.dao.RfqStatusDao;
import com.pentair.showcase.rfq.entity.Rfq;
import com.pentair.utils.DateUtil;

public class RfqCloseJob {
    private static Logger logger = LoggerFactory.getLogger(RfqCloseJob.class);

    @Autowired
    private RfqDao rfqDao;

    @Autowired
    private RfqStatusDao rfqStatusDao;

    @Transactional
    public void execute() {
        logger.info("RFQ定时任务开始执行...");
        List<Rfq> list = rfqDao.findBy("status.id", "pm2salesyes");
        for (Rfq rfq : list) {
            closeRfq(rfq);
        }

        list = rfqDao.findBy("status.id", "pm2salesyes2");
        for (Rfq rfq : list) {
            closeRfq(rfq);
        }

    }

    // PM审批通过后的第85天，系统发邮件提示sales，RFQ报价即将过期
    // PM审批通过后的第90天，RFQ报价自动关闭
    @Transactional
    private void closeRfq(Rfq rfq) {
        int days = DateUtil.daysBetweenTwoDate(rfq.getUpdateTime(), new Date());

        if (days > 84 && days < 90) {
            rfqDao.sendToCloseNoticeMail(rfq);
            logger.info("编号为" + rfq.getSn() + "的RFQ即将到期！");
        } else if (days >= 90) {
            rfq.setStatus(rfqStatusDao.get("close"));
            rfqDao.save(rfq);
            rfqDao.sendCloseNoticeMail(rfq);
            logger.info("编号为" + rfq.getSn() + "的RFQ已超时关闭！");
        }
    }
}
