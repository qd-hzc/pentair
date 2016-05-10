package com.pentair.showcase.email;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.pentair.showcase.rfq.entity.Rfq;
import com.pentair.utils.DateUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MailTemplateUtil {

    private static final String DEFAULT_ENCODING = "utf-8";

    private static Logger logger = LoggerFactory.getLogger(MailTemplateUtil.class);

    private Template template;
    private String mailTemplate;


    /**
     * 注入Freemarker引擎配置,构造Freemarker 邮件内容模板.
     */
    public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) throws IOException {
        //根据freemarkerConfiguration的templateLoaderPath载入文件.
        template = freemarkerConfiguration.getTemplate(mailTemplate, DEFAULT_ENCODING);
    }

    /**
     * 使用Freemarker生成RFQ邮件的内容.
     */
    public String generateRfqMailContent(Rfq rfq) {

        try {
            Map<String, String> context = new HashMap<String, String>();
            context.put("sn", rfq.getSn());
            context.put("customerName", rfq.getCustomerName());
            context.put("area", rfq.getOwnerSales().getArea().getName());
            context.put("brand", rfq.getSeries().getBrand().getName());
            context.put("series", rfq.getSeries().getName());
            context.put("pm", rfq.getSeries().getOwnerPM().getName());
            context.put("sales", rfq.getOwnerSales().getName());
            if (rfq.getOwnerAPP() != null) {
                context.put("app", rfq.getOwnerAPP().getName());
            } else {
                context.put("app", "");
            }
            if (rfq.getOwnerCE() != null) {
                context.put("ce", rfq.getOwnerCE().getName());
            } else {
                context.put("ce", "");
            }
            if (rfq.getOwnerCS() != null) {
                context.put("cs", rfq.getOwnerCS().getName());
            } else {
                context.put("cs", "");
            }
            context.put("inputTime", DateUtil.formatMinute(rfq.getInputTime()));
            context.put("updateTime", DateUtil.formatMinute(rfq.getUpdateTime()));
            context.put("status", rfq.getStatus().getName());
            if (rfq.getNotePm() != null) {
                context.put("notePm", rfq.getNotePm());
            } else {
                context.put("notePm", "");
            }

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
        } catch (IOException e) {
            logger.error("生成邮件内容失败, FreeMarker模板不存在", e);
            return "";
        } catch (TemplateException e) {
            logger.error("生成邮件内容失败, FreeMarker处理失败", e);
            return "";
        }
    }

    /**
     * 生成RFQ邮件的标题
     *
     * @param rfq
     * @return
     * @throws MessagingException
     */
    public String generateRfqMailSubject(Rfq rfq) {
        return "您有新的RFQ需要处理(编号:" + rfq.getSn() + ",状态:" + rfq.getStatus().getName();
    }

    public String generateRfqMailSubjectToClose(Rfq rfq) {
        return "编号为" + rfq.getSn() + "的RFQ将于" + (90 - DateUtil.daysBetweenTwoDate(rfq.getUpdateTime(), new Date())) + "天后超时自动关闭，请及时处理！";
    }

    public String generateRfqMailSubjectClose(Rfq rfq) {
        return "编号为" + rfq.getSn() + "的RFQ已超时自动关闭！";
    }

    public String getMailTemplate() {
        return mailTemplate;
    }

    public void setMailTemplate(String mailTemplate) {
        this.mailTemplate = mailTemplate;
    }
}