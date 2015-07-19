package com.cn.complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cn.jdbc.JDBC_Connection;

public class ShowComplaintStore {
	public List<ComplaintStoreBean> showcomplaintstore(){
		List<ComplaintStoreBean> list=new ArrayList<ComplaintStoreBean>();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * from complaint order by complaint_time desc";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				ComplaintStoreBean complaintstorebean=new ComplaintStoreBean();
				complaintstorebean.setId(rs.getInt("ID"));
				complaintstorebean.setComplaint_reason(rs.getString("complaint_reason"));
				complaintstorebean.setComplaint_time(df.format(rs.getTimestamp("complaint_time")));
				complaintstorebean.setOrder_time(df.format(rs.getTimestamp("order_time")));
				complaintstorebean.setStore_ID(rs.getString("store_ID"));
				complaintstorebean.setStore_telephone(rs.getString("store_telephone"));
				complaintstorebean.setUser_telephone(rs.getString("user_telephone"));
				complaintstorebean.setUsername(rs.getString("username"));
				complaintstorebean.setState(rs.getString("state"));
				list.add(complaintstorebean);	
			}
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return list;
	}
}
