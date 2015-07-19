package com.cn.store;

import java.sql.*;

import com.cn.jdbc.*;


public class AddStore {
	int response=0;
	public int addstore(StoreBean storebean){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
	try{
		
		conn=JDBC_Connection.getConnection();
		String sqlinfo="insert into store_login(store_ID,store_password) values(?,?)";
		pstm=conn.prepareStatement(sqlinfo);
		pstm.setString(1, storebean.getstore_ID());
		pstm.setString(2, storebean.getstore_password());
		pstm.executeUpdate();
		String sql="insert into store_info(store_ID,store_name,taste,lowest_consume,order_number,state,balance,store_telephone,address,longitude,latitude) values(?,?,?,?,?,?,?,?,?,?,?)";
		pstm=conn.prepareStatement(sql);
		pstm.setString(1, storebean.getstore_ID());
		pstm.setString(2,storebean.getstore_name());
		pstm.setString(3,storebean.gettaste());
		pstm.setString(4,storebean.getlowest_consume());
		pstm.setInt(5,storebean.getorder_number());
		pstm.setString(6, storebean.getstate());
		pstm.setDouble(7,storebean.getBalance());
		pstm.setString(8,storebean.getStore_telephone());
		pstm.setString(9, "");
		pstm.setDouble(10, 121.501921);
		pstm.setDouble(11, 30.846076);
		
		response=pstm.executeUpdate();
	}catch(SQLException e){
		System.out.println("”–“Ï≥££°");
		e.printStackTrace();
	}
	JDBC_Connection.free(rs, conn, pstm);
	return response;
  }

}
