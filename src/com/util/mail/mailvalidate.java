package com.util.mail;

public class mailvalidate {
	public void validate(String mailToAddress, String testcode){
		 MailSenderInfo mailInfo = new MailSenderInfo();  
	     mailInfo.setMailServerHost("smtp.163.com");    
	     mailInfo.setMailServerPort("25");    
	     mailInfo.setValidate(true);    
	     mailInfo.setUserName("weiqianghu93@163.com");    
	     mailInfo.setPassword("824714606hwq");//������������    
	     mailInfo.setFromAddress("weiqianghu93@163.com");    
	     mailInfo.setToAddress(mailToAddress);    
	     mailInfo.setSubject("����ô������֤");    
	     mailInfo.setContent("�������ĳ���ô��վ��֤�룺"+testcode+",�벻Ҫй¶��");    
	        //�������Ҫ�������ʼ�   
	     SimpleMailSender sms = new SimpleMailSender();   
	     sms.sendHtmlMail(mailInfo);//����html��ʽ  
	}
	
}
