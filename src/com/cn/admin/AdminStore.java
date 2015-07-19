package com.cn.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.jdbc.JDBC_Connection;
import com.cn.store.StoreBean;
public class AdminStore {
	public List<StoreBean> showstoreinfo(){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<StoreBean> list=new ArrayList<StoreBean>();
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * from store_info,store_login where store_info.store_ID=store_login.store_ID";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				StoreBean storebean=new StoreBean();
				storebean.setstore_ID(rs.getString("store_ID"));
				storebean.setstore_name(rs.getString("store_name"));
				storebean.setstate(rs.getString("state"));
				storebean.setstate(rs.getString("taste"));
				storebean.setlowest_consume(rs.getString("lowest_consume"));
				storebean.setorder_number(rs.getInt("order_number"));
				storebean.setBalance(rs.getDouble("balance"));
				storebean.setstore_password(rs.getString("store_password"));
				list.add(storebean);
			}
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return list;
		}

}
