package com.cn.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cn.jdbc.JDBC_Connection;
import com.cn.users.UsersVo;

import java.util.ArrayList;
import java.util.List;

public class AdminUser {
	public List<UsersVo> showuserinfo(){
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	List<UsersVo> list=new ArrayList<UsersVo>();
	try{
		conn=JDBC_Connection.getConnection();
		String sql="select * from user_info,user_login where user_info.username=user_login.username";
		pstm=conn.prepareStatement(sql);
		rs=pstm.executeQuery();
		while(rs.next()){
			UsersVo usersvo=new UsersVo();
			usersvo.setUsername(rs.getString("username"));
			usersvo.setPassword(rs.getString("password"));
			usersvo.setName(rs.getString("name"));
			usersvo.setTelephone(rs.getString("telephone"));
			usersvo.setAddress(rs.getString("address"));
			usersvo.setEmail(rs.getString("email"));
			usersvo.setqq(rs.getString("qq"));
			usersvo.setBalance(rs.getInt("balance"));
			list.add(usersvo);
		}
	}catch(SQLException e){
		System.out.println("”–“Ï≥££°");
		e.printStackTrace();
	}
	JDBC_Connection.free(rs, conn, pstm);
	return list;
	}

}
