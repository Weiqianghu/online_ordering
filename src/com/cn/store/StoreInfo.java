package com.cn.store;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cn.jdbc.*;
public class StoreInfo {
	public StoreBean storeinfo(String username,String password){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		StoreBean storeBean=new StoreBean();
		//List<StoreBean> list=new ArrayList<StoreBean>();
		
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * from store_info where store_ID = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			rs=pstm.executeQuery();
			System.out.println("已经执行！");
			while(rs.next()){
				//StoreBean storeBean=new StoreBean();
				storeBean.setstore_ID(username);
				storeBean.setstore_name(rs.getString("store_name"));
				storeBean.settaste(rs.getString("taste"));
				storeBean.setlowest_consume(rs.getString("lowest_consume"));
				storeBean.setorder_number(rs.getInt("order_number"));
				storeBean.setstate(rs.getString("state"));
				storeBean.setBalance(rs.getDouble("balance"));
				storeBean.setstore_password(password);
				storeBean.setLatitude(rs.getDouble("latitude"));
				storeBean.setLongitude(rs.getDouble("longitude"));
				storeBean.setStore_telephone(rs.getString("store_telephone"));
				storeBean.setAddress(rs.getString("address"));
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return storeBean;
	}

}
