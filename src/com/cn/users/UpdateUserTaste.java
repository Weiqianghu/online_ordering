package com.cn.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cn.jdbc.JDBC_Connection;

public class UpdateUserTaste {
	public void updatetaste(String username,String taste){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="update user_info set taste = ? where username = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, taste);
			pstm.setString(2, username);
			pstm.executeUpdate();
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
	}

}
