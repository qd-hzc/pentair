package com.pentair.showcase.jms.email;

import java.util.*;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.pentair.showcase.email.MailService;
import com.pentair.showcase.email.MailTemplateUtil;
import com.pentair.showcase.rfq.entity.Rfq;

/**
 * JMS用户变更消息生产者.
 * <p>
 * 使用jmsTemplate将用户变更消息分别发送到queue
 *
 * @author calvin
 */
@SuppressWarnings("unchecked")
public class NotifyMessageProducer {

    private JmsTemplate jmsTemplate;
    private Destination notifyQueue;

    @Autowired(required = false)
    private MailTemplateUtil mailTemplateUtil;

    public void sendNormalNoticeMail(String[] to, Rfq rfq) {
        if (to != null && to.length > 0) {
            String subject = mailTemplateUtil.generateRfqMailSubject(rfq);
            String content = mailTemplateUtil.generateRfqMailContent(rfq);
            List<String> sentTos = new ArrayList<String>();
            for (int i = 0; i < to.length; i++) {
                if (sentTos.contains(to[i])) {
                    continue;
                } else {
                    sentTos.add(to[i]);
                }
                Map map = new HashMap();

                map.put("to", to[i]);
                map.put("subject", subject);
                map.put("content", content);
//System.out.println("email=" + map);
                jmsTemplate.convertAndSend(notifyQueue, map);
            }
        }

    }

    public void sendToCloseNoticeMail(String[] to, Rfq rfq) {
        if (to != null && to.length > 0) {
            String subject = mailTemplateUtil.generateRfqMailSubjectToClose(rfq);
            String content = mailTemplateUtil.generateRfqMailContent(rfq);
            List<String> sentTos = new ArrayList<String>();
            for (int i = 0; i < to.length; i++) {
                if (sentTos.contains(to[i])) {
                    continue;
                } else {
                    sentTos.add(to[i]);
                }
                Map map = new HashMap();

                map.put("to", to[i]);
                map.put("subject", subject);
                map.put("content", content);
//System.out.println("email=" + map);
                jmsTemplate.convertAndSend(notifyQueue, map);
            }
        }
    }

    public void sendCloseNoticeMail(String[] to, Rfq rfq) {
        if (to != null && to.length > 0) {
            String subject = mailTemplateUtil.generateRfqMailSubjectClose(rfq);
            String content = mailTemplateUtil.generateRfqMailContent(rfq);
            List<String> sentTos = new ArrayList<String>();
            for (int i = 0; i < to.length; i++) {
                if (sentTos.contains(to[i])) {
                    continue;
                } else {
                    sentTos.add(to[i]);
                }
                Map map = new HashMap();

                map.put("to", to[i]);
                map.put("subject", subject);
                map.put("content", content);
//System.out.println("email=" + map);
                jmsTemplate.convertAndSend(notifyQueue, map);
            }
        }
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setNotifyQueue(Destination notifyQueue) {
        this.notifyQueue = notifyQueue;
    }

}
