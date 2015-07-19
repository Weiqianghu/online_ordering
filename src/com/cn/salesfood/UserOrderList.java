package com.cn.salesfood;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cn.food.foodBean;
import com.cn.jdbc.*;
import com.cn.users.UsersVo;
public class UserOrderList {
	
	public List<UserOrderListBean> showuserorderlist(UsersVo usersvo){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		List<UserOrderListBean> userorderlist=new ArrayList<UserOrderListBean>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			conn=JDBC_Connection.getConnection();
			String sql="SELECT * FROM ordering where username= ? group by order_time order by order_time desc";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, usersvo.getUsername());
			rs1=pstm.executeQuery();
			while(rs1.next()){
				double total_price=0.0;
				UserOrderListBean userorderlistbean=new UserOrderListBean();
				userorderlistbean.setTelephone(rs1.getString("telephone"));
				userorderlistbean.setAddress(rs1.getString("address"));
				userorderlistbean.setOrder_time(df.format(rs1.getTimestamp("order_time")));
				userorderlistbean.setOrder_state(rs1.getString("order_state"));
				sql="SELECT * FROM ordering,sales,food,store_info where ordering.sales_ID=sales.ID and sales.store_ID=store_info.store_ID and sales.food_ID=food.food_ID and  ordering.username= ? and ordering.order_time= ?";
				pstm=conn.prepareStatement(sql);
				pstm.setString(1, usersvo.getUsername());
				pstm.setTimestamp(2, rs1.getTimestamp("order_time"));
				rs2=pstm.executeQuery();
				List<foodBean> foodlist=new  ArrayList<foodBean>();
				while(rs2.next()){
					foodBean foodbean=new foodBean();
					foodbean.setFood_ID(rs2.getInt("food_ID"));
					foodbean.setName(rs2.getString("name"));
					foodbean.setPrice(rs2.getString("price"));
					foodbean.setStore_name(rs2.getString("store_name"));
					foodbean.setStore_telephone(rs2.getString("store_telephone"));
					foodbean.setNum(rs2.getInt("num"));
					foodlist.add(foodbean);
					total_price=total_price+Double.valueOf(rs2.getString("price"))*rs2.getInt("num");
				}
				userorderlistbean.setFoodlist(foodlist);
				userorderlistbean.setTotal_price(total_price);
				userorderlist.add(userorderlistbean);
				
			}
		}
		catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs1, conn, pstm);
		JDBC_Connection.free(rs2, conn, pstm);
		return userorderlist;
	}

}
