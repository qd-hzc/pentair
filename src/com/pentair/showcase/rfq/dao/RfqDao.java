package com.pentair.showcase.rfq.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.pentair.showcase.common.dao.RoleDao;
import com.pentair.showcase.common.dao.UserDao;
import com.pentair.showcase.common.entity.Role;
import com.pentair.showcase.common.entity.User;
import com.pentair.showcase.jms.email.NotifyMessageProducer;
import com.pentair.showcase.rfq.entity.Rfq;
import com.pentair.showcase.rfq.entity.RfqRole;

@Component
public class RfqDao extends HibernateDao<Rfq, String> {

	@Autowired
	private NotifyMessageProducer notifyProducer; //JMS消息发送
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	public void sendNormalNoticeMail(Rfq rfq){
		String[] to=getRfqEmails(rfq);
		if(to!=null&&to.length>0){
			notifyProducer.sendNormalNoticeMail(to,rfq);
		}
	}
	
	public void sendToCloseNoticeMail(Rfq rfq){
		String[] to=getRfqEmails(rfq);
		if(to!=null&&to.length>0){
			notifyProducer.sendToCloseNoticeMail(to,rfq);
		}
	}
	
	public void sendCloseNoticeMail(Rfq rfq){
		String[] to=getRfqEmails(rfq);
		if(to!=null&&to.length>0){
			notifyProducer.sendCloseNoticeMail(to,rfq);
		}
	}

	/**
	 * 获得某个RFQ各状态下接受邮件的对象
	 * @param status
	 * @return
	 */
	private String[] getRfqEmails(Rfq rfq){
		List<String> emails=new ArrayList<String>();
		for(RfqRole rfqRole:rfq.getStatus().getRfqRoles()){
			String email=null;
			if(rfqRole.getEmailTemplate()!=null && !"".equals(rfqRole.getEmailTemplate())){

				if("SALES".equals(rfqRole.getRole())){
					email=rfq.getOwnerSales().getEmail();
				}else if("APP".equals(rfqRole.getRole())){
					email=rfq.getOwnerAPP().getEmail();
				}else if("CE".equals(rfqRole.getRole())){
					email=rfq.getOwnerCE().getEmail();
				}else if("CS".equals(rfqRole.getRole())){
					email=rfq.getOwnerCS().getEmail();
				}else if("PM".equals(rfqRole.getRole())){
					email=rfq.getSeries().getOwnerPM().getEmail();
				}else if("AM".equals(rfqRole.getRole())){
					email=rfq.getSeries().getBrand().getOwnerAM().getEmail();
				}else if("CM".equals(rfqRole.getRole())){
					email=rfq.getSeries().getBrand().getOwnerCM().getEmail();
				}else if("CSM".equals(rfqRole.getRole())){
					email=rfq.getSeries().getBrand().getOwnerCSM().getEmail();
				}else if("MM".equals(rfqRole.getRole())){
					email=rfq.getSeries().getBrand().getOwnerMM().getEmail();
				}else if("MD".equals(rfqRole.getRole())){
					email=rfq.getSeries().getBrand().getOwnerMD().getEmail();
				}else if("GM".equals(rfqRole.getRole())){
					email=rfq.getSeries().getBrand().getOwnerGM().getEmail();
				}
				else if("3RD".equals(rfqRole.getRole())) {
					List<User> users = userDao.getUsersByRoleName("3RD");
					for(User user : users) {
						emails.add(user.getEmail());
					}
				}

				if(email!=null && !"".equals(email)){
					emails.add(email);
				}
				
				if("DE".equals(rfqRole.getRole())||"FD".equals(rfqRole.getRole())){//DE,FD暂时未绑定Brand
					Role role = roleDao.findUniqueBy("shortName", rfqRole.getRole());
					if(role!=null){
						for(User user:role.getUsers()){
							if(user.getEmail()!=null && !"".equals(user.getEmail())){
								emails.add(user.getEmail());
							}
						}
					}
				}
			}
		}
		
		return (String[])emails.toArray(new String[emails.size()]);		
	}
}
