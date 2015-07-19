package com.cn.users;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cn.jdbc.*;

public class UserPage {
	public UsersVo userinfo(String username,String password){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		UsersVo usersvo=new UsersVo();
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * from user_info where username = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			rs=pstm.executeQuery();
			System.out.println("已经执行！");
			while(rs.next()){
				usersvo.setUsername(username);
				usersvo.setName(rs.getString("name"));
				usersvo.setTelephone(rs.getString("telephone"));
				usersvo.setAddress(rs.getString("address"));
				usersvo.setqq(rs.getString("qq"));
				usersvo.setEmail(rs.getString("email"));
				usersvo.setBalance(rs.getDouble("balance"));
				usersvo.setSupport(rs.getInt("support"));
				usersvo.setTaste(rs.getString("taste"));
				usersvo.setPassword(password);
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return usersvo;
	}
	
	public List<String> usertaste(){
		List<String> usertastelist=new ArrayList<String>();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select distinct taste from store_info";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				usertastelist.add(rs.getString("taste"));
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return usertastelist;
	}

}
