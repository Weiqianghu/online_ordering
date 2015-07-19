package com.cn.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.cn.jdbc.JDBC_Connection;

public class ValidateEmail {
	public  void insertTestCode(String email,String testcode){
		
		
		java.util.Date date=new java.util.Date();
   	    Timestamp sendtime=new Timestamp(date.getTime());
   	    ResultSet rs=null;
   	    Connection conn=null;
		PreparedStatement pstm=null;
		String sql="delete from validate where email = ?";
		try{
			conn=JDBC_Connection.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,email);
			pstm.executeUpdate();
			sql="insert into validate(email,testcode,sendtime) values(?,?,?)";
			conn=JDBC_Connection.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,email);
			pstm.setString(2,testcode);
			pstm.setTimestamp(3,sendtime);
			pstm.executeUpdate();
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
	};

}
