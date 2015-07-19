package com.cn.complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.cn.jdbc.JDBC_Connection;

public class ComplaintStore {
	public boolean complaint(String username,Timestamp order_time,String complaint_reason,String user_telephone){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		boolean ok=false;
		String store_telephone="";
		String store_ID="";
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select distinct store_info.store_ID"+
				" from ordering,sales,store_info"+
				" where ordering.sales_ID=sales.ID "+
				" and sales.store_ID=store_info.store_ID "+
				" and ordering.username= ? "+
				" and ordering.order_time= ? ";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setTimestamp(2,order_time);
			rs=pstm.executeQuery();
			if(rs.next()){
				store_ID=rs.getString("store_ID");
			}
			conn=JDBC_Connection.getConnection();
			sql="select store_telephone from store_info where store_ID=?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, store_ID);
			rs=pstm.executeQuery();
			if(rs.next()){
				store_telephone=rs.getString("store_telephone");
			}
			java.util.Date date=new java.util.Date();
			Timestamp complaint_time=new Timestamp(date.getTime());
			sql="insert into complaint(username,store_ID,order_time,complaint_reason,store_telephone,user_telephone,complaint_time,state) values(?,?,?,?,?,?,?,?)";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2,store_ID);
			pstm.setTimestamp(3, order_time);
			pstm.setString(4,complaint_reason);
			pstm.setString(5,store_telephone);
			pstm.setString(6,user_telephone);
			pstm.setTimestamp(7, complaint_time);
			pstm.setString(8, "未处理");
			pstm.executeUpdate();
			ok=true;
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return ok;
	}

}
