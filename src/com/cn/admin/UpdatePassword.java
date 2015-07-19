package com.cn.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cn.jdbc.JDBC_Connection;

public class UpdatePassword {
	public boolean updatepassword(String email,String password){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		boolean issuccess=false;
		String username=null;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select username from user_info where email = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, email);
			rs=pstm.executeQuery();
			if(rs.next()){
				username=rs.getString("username");
			}
			sql="update user_login set password= ?  where username = ? ";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,password);
			pstm.setString(2,username);
			pstm.executeUpdate();
			issuccess=true;
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return issuccess;
	}
}
