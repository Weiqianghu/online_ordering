package com.cn.salesfood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cn.food.foodBean;
import com.cn.jdbc.JDBC_Connection;
import com.cn.store.StoreBean;

public class StoreOrderList {
	public List<UserOrderListBean> showstoreorderlist(StoreBean storebean){
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs1=null;
	ResultSet rs2=null;
	List<UserOrderListBean> storeorderlist=new ArrayList<UserOrderListBean>();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	try{
		conn=JDBC_Connection.getConnection();
		String sql="SELECT * FROM ordering,sales,store_info,user_info where ordering.username=user_info.username and ordering.sales_ID=sales.ID and sales.store_ID=store_info.store_ID and  store_info.store_ID= ?  group by order_time,ordering.username order by order_time desc";;
		pstm=conn.prepareStatement(sql);
		pstm.setString(1,storebean.getstore_ID());
		rs1=pstm.executeQuery();
		while(rs1.next()){
			Double total_price=0.0;
			UserOrderListBean storeorderlistbean=new UserOrderListBean();
			storeorderlistbean.setTelephone(rs1.getString("telephone"));
			storeorderlistbean.setAddress(rs1.getString("address"));
			storeorderlistbean.setOrder_time(df.format(rs1.getTimestamp("order_time")));
			storeorderlistbean.setOrder_state(rs1.getString("order_state"));
			storeorderlistbean.setUsername(rs1.getString("username"));
			storeorderlistbean.setName(rs1.getString("name"));
			sql="select * from ordering,sales,food,store_info where ordering.sales_ID=sales.ID and sales.store_ID=store_info.store_ID and sales.food_ID=food.food_ID and ordering.username= ? and ordering.order_time= ? and store_info.store_ID= ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, rs1.getString(2));
			pstm.setTimestamp(2, rs1.getTimestamp("order_time"));
			pstm.setString(3,storebean.getstore_ID());
			rs2=pstm.executeQuery();
			List<foodBean> foodlist=new  ArrayList<foodBean>();
			while(rs2.next()){
				foodBean foodbean=new foodBean();
				foodbean.setFood_ID(rs2.getInt("food_ID"));
				foodbean.setName(rs2.getString("name"));
				foodbean.setPrice(rs2.getString("price"));
				foodbean.setNum(rs2.getInt("num"));
				foodlist.add(foodbean);
				total_price=total_price+Double.valueOf(rs2.getString("price"))*rs2.getInt("num");
			}
			storeorderlistbean.setFoodlist(foodlist);
			storeorderlistbean.setTotal_price(total_price);
			storeorderlist.add(storeorderlistbean);
		}
	}catch(SQLException e){
		System.out.println("”–“Ï≥££°");
		e.printStackTrace();
	}
	JDBC_Connection.free(rs1, conn, pstm);
	JDBC_Connection.free(rs2, conn, pstm);
	return storeorderlist;
	
	}
	

}
