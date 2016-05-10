package com.pentair.showcase.jms.simple;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentair.showcase.email.SimpleMailService;

/**
 * 消息的异步被动接收者.
 * <p>
 * 使用Spring的MessageListenerContainer侦听消息并调用本Listener进行处理.
 *
 * @author calvin
 */
public class NotifyMessageListener implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(NotifyMessageListener.class);

    @Autowired(required = false)
    private SimpleMailService simpleMailService;

    /**
     * MessageListener回调函数.
     */
    public void onMessage(Message message) {
        try {
            MapMessage mapMessage = (MapMessage) message;
            //打印消息详情
            logger.info("UserName:" + mapMessage.getString("userName") + ", Email:" + mapMessage.getString("email"));

            //发送邮件
            if (simpleMailService != null) {
                simpleMailService.sendNotificationMail(mapMessage.getString("userName"));
            }
        } catch (Exception e) {
            logger.error("处理消息时发生异常.", e);
        }
    }
}
