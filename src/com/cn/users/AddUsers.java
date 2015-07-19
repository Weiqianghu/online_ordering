package com.cn.users;

import java.sql.*;

import com.cn.jdbc.*;


public class AddUsers {
	int response=0;
	public int addusers(UsersVo usersVo){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
	try{
		
		conn=JDBC_Connection.getConnection();
		String sql="insert into user_login(username,password) values(?,?)";
		pstm=conn.prepareStatement(sql);
		pstm.setString(1, usersVo.getUsername());
		pstm.setString(2,usersVo.getPassword());
		pstm.executeUpdate();
		String sqlinfo="insert into user_info(username,name,telephone,address,qq,email,balance,support,taste) values(?,?,?,?,?,?,?,?,?)";
		pstm=conn.prepareStatement(sqlinfo);
		pstm.setString(1, usersVo.getUsername());
		pstm.setString(2, "");
		pstm.setString(3, "");
		pstm.setString(4, "");
		pstm.setString(5, "");
		pstm.setString(6, usersVo.getEmail());
		pstm.setDouble(7,0);
		pstm.setInt(8,0);
		pstm.setString(9,"");
		response=pstm.executeUpdate();
		
	}catch(SQLException e){
		System.out.println("”–“Ï≥££°");
		e.printStackTrace();
	}
	JDBC_Connection.free(rs, conn, pstm);
	return response;
  }
}
