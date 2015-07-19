package com.cn.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cn.jdbc.JDBC_Connection;

public class ExistEmail {
	public int existemail(String email){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		int resp=0;
		String sql="select * from user_info where email = ? ";
		try{
			conn=JDBC_Connection.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, email);
			rs=pstm.executeQuery();
			while(rs.next())
				resp++;
		}catch(SQLException e){
			System.out.println("”–“Ï≥£!");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return resp;
	}

}
