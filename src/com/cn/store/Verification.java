package com.cn.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cn.jdbc.JDBC_Connection;

public class Verification {
	
	public int verification(String whichone,String username,String password){
		
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		String sql;
		boolean istrue=false;
		System.out.println("whichone:"+whichone+"username:"+username+"password:"+password);
		try{
			conn=JDBC_Connection.getConnection();
			if(whichone.equals("user")){
			     sql="select * from user_login where username=? and password= ?" ;
			}
			else if(whichone.equals("shop"))
			{
				 sql="select * from store_login where store_ID=? and store_password= ?" ;
			}
			else{
				sql="select * from admin_login where username=? and password= ?" ;
			}
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			rs=pstm.executeQuery();
			if(rs.next()){
				istrue=true;;
				}
			else{
				istrue=false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(istrue==true){
			return 1;
		}
		else
			return 0;
	}

}
