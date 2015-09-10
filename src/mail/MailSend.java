package mail;

public class MailSend {
	public boolean mailSend(String name,String emailAddress,String content){
		 MailSenderInfo mailInfo = new MailSenderInfo();  
	     mailInfo.setMailServerHost("smtp.163.com");    
	     mailInfo.setMailServerPort("25");    
	     mailInfo.setValidate(true);    
	     mailInfo.setUserName("weiqianghu93@163.com");    
	     mailInfo.setPassword("824714606hwq");//您的邮箱密码    
	     mailInfo.setFromAddress("weiqianghu93@163.com");    
	     mailInfo.setToAddress("weiqianghu_ecust@163.com");    
	     mailInfo.setSubject("个人主页邮件");    
	     mailInfo.setContent("这是"+name+"发来的邮件，邮箱为："+emailAddress+"。\n内容为："+content);    
	        //这个类主要来发送邮件   
	    SimpleMailSender simpleMailSender=new SimpleMailSender();
	     //return  simpleMailSender.sendHtmlMail(mailInfo);//发送html格式  
	     return  SimpleMailSender.sendHtmlMail(mailInfo);
	}
	public static void main(String args[]){
		
		MailSend mailSend=new MailSend();
		mailSend.mailSend("胡伟强", "weiqianghu93@163.com", "您好！");
	}
}
