package com.cn.store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cn.jdbc.JDBC_Connection;
public class Recommend {
	
	public List<RecommendBean> recommendfood(String username){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<RecommendBean> list=new ArrayList<RecommendBean>();
			try{
				conn=JDBC_Connection.getConnection();
				String sql="select food.food_ID,sales.store_ID,price,name,sales_volume,count(food.food_ID),store_info.store_name,store_info.state"
						+ " from ordering,sales,food,store_info"
						+ " where ordering.sales_ID=sales.ID and sales.food_ID=food.food_ID and store_info.store_ID=sales.store_ID and username= ?"
						+ " group by  food.food_ID"
						+ " order by count(food.food_ID) desc limit 2";
			
				pstm=conn.prepareStatement(sql);
				pstm.setString(1, username);
				rs=pstm.executeQuery();
				while(rs.next()){
					RecommendBean recommendBean=new RecommendBean();
					recommendBean.setFood_ID(rs.getString("food_ID"));
					recommendBean.setFood_name(rs.getString("name"));
					recommendBean.setFood_price(rs.getString("price"));
					recommendBean.setSales_volume(rs.getString("sales_volume"));
					recommendBean.setStore_ID(rs.getString("store_ID"));
					recommendBean.setStore_name(rs.getString("store_name"));
					recommendBean.setStore_state(rs.getString("state"));
					list.add(recommendBean);
				}
			}catch(SQLException e){
				System.out.println("有异常！");
				e.printStackTrace();
			}
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select food.food_ID,sales.store_ID,price,name,sales_volume,count(food.food_ID),store_info.store_name,store_info.state"
					+ " from ordering,sales,food,store_info"
					+ " where ordering.sales_ID=sales.ID and sales.food_ID=food.food_ID and store_info.store_ID=sales.store_ID"
					+ " group by  food.food_ID"
					+ " order by count(food.food_ID) desc limit 2";
			
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				RecommendBean recommendBean=new RecommendBean();
				recommendBean.setFood_ID(rs.getString("food_ID"));
				recommendBean.setFood_name(rs.getString("name"));
				recommendBean.setFood_price(rs.getString("price"));
				recommendBean.setSales_volume(rs.getString("sales_volume"));
				recommendBean.setStore_ID(rs.getString("store_ID"));
				recommendBean.setStore_name(rs.getString("store_name"));
				recommendBean.setStore_state(rs.getString("state"));
				list.add(recommendBean);
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return list;
	}
	
	public List<RecommendBean> irecommendfood(){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<RecommendBean> list=new ArrayList<RecommendBean>();
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select food.food_ID,sales.store_ID,price,name,sales_volume,count(food.food_ID),store_info.store_name,store_info.state"
					+ " from ordering,sales,food,store_info"
					+ " where ordering.sales_ID=sales.ID and sales.food_ID=food.food_ID and store_info.store_ID=sales.store_ID"
					+ " group by  food.food_ID"
					+ " order by count(food.food_ID) desc limit 2";
			
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				RecommendBean recommendBean=new RecommendBean();
				recommendBean.setFood_ID(rs.getString("food_ID"));
				recommendBean.setFood_name(rs.getString("name"));
				recommendBean.setFood_price(rs.getString("price"));
				recommendBean.setSales_volume(rs.getString("sales_volume"));
				recommendBean.setStore_ID(rs.getString("store_ID"));
				recommendBean.setStore_name(rs.getString("store_name"));
				recommendBean.setStore_state(rs.getString("state"));
				list.add(recommendBean);
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return list;
	}

}
