package com.cn.admin;

import java.util.Random;

public class ValidatAassist {
	public String getStringRandom(int length) {  
        
			String val = "";  
			Random random = new Random();  
          
			//参数length，表示生成几位随机数  
			for(int i = 0; i < length; i++) {  
              
				String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
				//输出字母还是数字  
				if( "char".equalsIgnoreCase(charOrNum) ) {  
					//输出是大写字母还是小写字母  
					int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
					val += (char)(random.nextInt(26) + temp);  
				} else if( "num".equalsIgnoreCase(charOrNum) ) {  
					val += String.valueOf(random.nextInt(10));  
				}  
			}  
			return val;  
	}  
	public  boolean  checkMail(String mail) {
		// 需求:校验邮件地址
		//String regex="/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/";；
		String regex = "[a-zA-Z0-9_]{1,20}+@[a-zA-Z0-9]+(\\.[a-zA-Z]+){1,3}";
		return mail.matches(regex);
		}
}
