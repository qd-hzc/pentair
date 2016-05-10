package com.pentair.showcase.jms.advanced;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息的异步被动接收者.
 * <p>
 * 使用Spring的MessageListenerContainer侦听消息并调用本Listener进行处理.
 *
 * @author calvin
 */
public class AdvancedNotifyMessageListener implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(AdvancedNotifyMessageListener.class);

    /**
     * MessageListener回调函数.
     */
    public void onMessage(Message message) {
        try {
            MapMessage mapMessage = (MapMessage) message;

            //打印消息详情
            logger.info("UserName:" + mapMessage.getString("userName") + ", Email:" + mapMessage.getString("email")
                    + ", ObjectType:" + mapMessage.getStringProperty("objectType"));
        } catch (Exception e) {
            logger.error("处理消息时发生异常.", e);
        }
    }
}
