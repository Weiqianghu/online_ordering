package com.cn.admin;

import java.util.Random;

public class ValidatAassist {
	public String getStringRandom(int length) {  
        
			String val = "";  
			Random random = new Random();  
          
			//����length����ʾ���ɼ�λ�����  
			for(int i = 0; i < length; i++) {  
              
				String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
				//�����ĸ��������  
				if( "char".equalsIgnoreCase(charOrNum) ) {  
					//����Ǵ�д��ĸ����Сд��ĸ  
					int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
					val += (char)(random.nextInt(26) + temp);  
				} else if( "num".equalsIgnoreCase(charOrNum) ) {  
					val += String.valueOf(random.nextInt(10));  
				}  
			}  
			return val;  
	}  
	public  boolean  checkMail(String mail) {
		// ����:У���ʼ���ַ
		//String regex="/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/";��
		String regex = "[a-zA-Z0-9_]{1,20}+@[a-zA-Z0-9]+(\\.[a-zA-Z]+){1,3}";
		return mail.matches(regex);
		}
}
