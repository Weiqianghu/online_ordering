package mail;

public class MailSend {
	public boolean mailSend(String name,String emailAddress,String content){
		 MailSenderInfo mailInfo = new MailSenderInfo();  
	     mailInfo.setMailServerHost("smtp.163.com");    
	     mailInfo.setMailServerPort("25");    
	     mailInfo.setValidate(true);    
	     mailInfo.setUserName("weiqianghu93@163.com");    
	     mailInfo.setPassword("824714606hwq");//������������    
	     mailInfo.setFromAddress("weiqianghu93@163.com");    
	     mailInfo.setToAddress("weiqianghu_ecust@163.com");    
	     mailInfo.setSubject("������ҳ�ʼ�");    
	     mailInfo.setContent("����"+name+"�������ʼ�������Ϊ��"+emailAddress+"��\n����Ϊ��"+content);    
	        //�������Ҫ�������ʼ�   
	    SimpleMailSender simpleMailSender=new SimpleMailSender();
	     //return  simpleMailSender.sendHtmlMail(mailInfo);//����html��ʽ  
	     return  SimpleMailSender.sendHtmlMail(mailInfo);
	}
	public static void main(String args[]){
		
		MailSend mailSend=new MailSend();
		mailSend.mailSend("��ΰǿ", "weiqianghu93@163.com", "���ã�");
	}
}
