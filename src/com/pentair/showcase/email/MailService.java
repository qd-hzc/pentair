package com.pentair.showcase.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 普通邮件服务类.
 * 
 * 演示由Freemarker引擎生成的的html格式邮件, 并带有附件.
 * 
 * @author James
 */
public class MailService {

	private static final String DEFAULT_ENCODING = "utf-8";

	private static Logger logger = LoggerFactory.getLogger(MailService.class);

	private JavaMailSender mailSender;
	private String mailFrom;

	/**
	 * Spring的MailSender.
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 发送邮件
	 */
	public void sendMail(String to,String subject,String content) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);

			helper.setFrom(mailFrom);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			mailSender.send(msg);
			
			logger.info("邮件已发送至"+to);
		} catch (MessagingException e) {
			logger.error("构造邮件失败", e);
		} catch (Exception e) {
			logger.error("发送邮件失败");
//			logger.error("发送邮件失败", e);
		}
	}
	
	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

}
