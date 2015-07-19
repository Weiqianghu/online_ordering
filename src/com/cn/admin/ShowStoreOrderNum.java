package com.cn.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.jdbc.JDBC_Connection;

public class ShowStoreOrderNum {
	public List<StoreOrderNumBean> showstoreordernum(){
		List<StoreOrderNumBean> list=new ArrayList<StoreOrderNumBean>();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="SELECT sum(num),order_time,ordering.ID,store_info.store_name,store_info.store_ID"+
						" FROM ordering,sales,store_info "+
						//" WHERE DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(order_time)"+
						" where ordering.sales_ID=sales.ID "+
						" and sales.store_ID=store_info.store_ID "+
						" and ordering.order_state='已处理' "+
						" group by store_info.store_ID "+
						" order by sum(num) desc limit 10";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				StoreOrderNumBean storeordernumbean=new StoreOrderNumBean();
				storeordernumbean.setStore_ID(rs.getString("store_ID"));
				storeordernumbean.setStore_name(rs.getString("store_name"));
				storeordernumbean.setOrder_num(rs.getString("sum(num)"));
				list.add(storeordernumbean);
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return list;
	}

}
