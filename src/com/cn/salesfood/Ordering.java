package com.cn.salesfood;
import java.sql.*;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.cn.jdbc.*;
public class Ordering {
	int response=0;
	public int ordering(OrderingBean orderingbean,List<String> basket_ID_list){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		int sales_ID=0;
		int num=1;Double user_balance=0.0,total_price=Double.valueOf(orderingbean.getPrice());
		Double lowest_consume=0.0;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * from user_info where username = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,orderingbean.getUsername());
			rs=pstm.executeQuery();
			while(rs.next()){
				user_balance=rs.getDouble("balance");
			}
			
			conn=JDBC_Connection.getConnection();
			sql="select * from store_info where store_ID = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,orderingbean.getStore_ID());
			rs=pstm.executeQuery();
			
			while(rs.next()){
				lowest_consume=rs.getDouble("lowest_consume");
			}
			
			if(user_balance<total_price){
				return -1;
			}
			else if(lowest_consume>total_price)
			{
				return -2;
			}
			else{
				java.util.Date date=new java.util.Date();
				Timestamp order_time=new Timestamp(date.getTime());
				for(int i=0;i<basket_ID_list.size();i++){
					
					sql="select * from basket,user_info,sales,store_info,food where basket.username=user_info.username and basket.sales_ID=sales.ID and sales.store_ID=store_info.store_ID and sales.food_ID = food.food_Id and basket.username = ? and basket.ID = ?";
					pstm=conn.prepareStatement(sql);
					pstm.setString(1,orderingbean.getUsername());
					pstm.setInt(2,Integer.parseInt(basket_ID_list.get(i)));
			     	rs=pstm.executeQuery();
			     	while(rs.next()){
			     		sales_ID=rs.getInt("sales_ID");
			     		num=rs.getInt("num");
			     		sql="insert into ordering(username,order_time,order_state,telephone,address,num,sales_ID) values(?,?,?,?,?,?,?)";
			     		pstm=conn.prepareStatement(sql);
			     		pstm.setString(1, orderingbean.getUsername());
			     		pstm.setTimestamp(2, order_time);
			     		pstm.setString(3, "未处理");
			     		pstm.setString(4, orderingbean.getTelephone());
			     		pstm.setString(5, orderingbean.getAddress());
			     		pstm.setInt(6, num);
			     		pstm.setInt(7,sales_ID);
			     		response=pstm.executeUpdate();
			     		
			     		sql="update user_info set balance=balance - ? where username = ?";
			     		pstm=conn.prepareStatement(sql);
			     		pstm.setDouble(1, rs.getDouble("price")*rs.getInt("num"));
			     		pstm.setString(2,orderingbean.getUsername());
			     		pstm.executeUpdate();
			     		
			     		sql="delete from basket where ID = ?";
			     		pstm=conn.prepareStatement(sql);
			     		//pstm.setString(1,orderingbean.getUsername());
			     		pstm.setInt(1,Integer.parseInt(basket_ID_list.get(i)));
			     		pstm.executeUpdate();
			     	}
				}
			}
			
		}
		catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return response;
	}
}
