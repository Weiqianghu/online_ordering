package com.util.mail;

public class mailvalidate {
	public void validate(String mailToAddress, String testcode){
		 MailSenderInfo mailInfo = new MailSenderInfo();  
	     mailInfo.setMailServerHost("smtp.163.com");    
	     mailInfo.setMailServerPort("25");    
	     mailInfo.setValidate(true);    
	     mailInfo.setUserName("weiqianghu93@163.com");    
	     mailInfo.setPassword("824714606hwq");//您的邮箱密码    
	     mailInfo.setFromAddress("weiqianghu93@163.com");    
	     mailInfo.setToAddress(mailToAddress);    
	     mailInfo.setSubject("吃了么邮箱验证");    
	     mailInfo.setContent("这是您的吃了么网站验证码："+testcode+",请不要泄露！");    
	        //这个类主要来发送邮件   
	     SimpleMailSender sms = new SimpleMailSender();   
	     sms.sendHtmlMail(mailInfo);//发送html格式  
	}
	
}
