package com.cn.complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.cn.jdbc.JDBC_Connection;

public class ExistComplaint {
	public boolean existcomplaint(String username,Timestamp order_time){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		boolean exist=false;
		
		String sql="select * from complaint where username= ? and order_time=  ?";
		
		try{
			conn=JDBC_Connection.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setTimestamp(2, order_time);
			rs=pstm.executeQuery();
			while(rs.next())
				exist=true;;
		}catch(SQLException e){
			System.out.println("”–“Ï≥£!");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		
		return exist;
	}

}
