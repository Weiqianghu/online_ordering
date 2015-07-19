package com.cn.apriorialgorithm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.jdbc.JDBC_Connection;
import com.cn.store.RecommendBean;


public class AprioriRecommend {
	public List<AprioriRecommendBean> getOrder_ID(){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<AprioriRecommendBean> order_ID_list=new ArrayList<AprioriRecommendBean>();
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select username,order_time"+
					" from ordering"+ 
					" group by username,order_time"+
					" order by ID";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				AprioriRecommendBean apriorirecommendbean=new AprioriRecommendBean();
				apriorirecommendbean.setUsername(rs.getString("username"));
				apriorirecommendbean.setOrder_time(rs.getTimestamp("order_time"));
				order_ID_list.add(apriorirecommendbean);	
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return order_ID_list;
	}
	
	public List<List<RecommendBean>> getCreatList(List<AprioriRecommendBean> list){
		List<List<RecommendBean>> creatlist=new ArrayList<List<RecommendBean>>();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		for(int i=0;i<list.size();i++){
			List<RecommendBean> recommendbeanlist=new ArrayList<RecommendBean>();
			try{
				conn=JDBC_Connection.getConnection();
				String sql="select distinct food.*,store_info.*"+
						" from ordering,sales,food,store_info"+
						" where ordering.sales_ID=sales.ID"+
						" and sales.food_ID=food.food_ID"+
						" and sales.store_ID=store_info.store_ID"+
						" and ordering.username= ?"+
						" and ordering.order_time= ?";
				pstm=conn.prepareStatement(sql);
				pstm.setString(1, list.get(i).getUsername());
				pstm.setTimestamp(2, list.get(i).getOrder_time());
				rs=pstm.executeQuery();
				while(rs.next()){
					RecommendBean recommendbean=new RecommendBean();
					recommendbean.setFood_ID(rs.getString("food_ID"));
					recommendbean.setFood_name(rs.getString("name"));
					recommendbean.setFood_price(rs.getString("price"));
					recommendbean.setSales_volume(rs.getString("sales_volume"));
					recommendbean.setStore_ID(rs.getString("store_ID"));
					recommendbean.setStore_name(rs.getString("store_name"));
					recommendbean.setStore_state(rs.getString("state"));
					recommendbeanlist.add(recommendbean);
					
				}
			}catch(SQLException e){
				System.out.println("有异常！");
				e.printStackTrace();
			}
			JDBC_Connection.free(rs, conn, pstm);
			creatlist.add(recommendbeanlist);
		}
		return creatlist;
	}
	
	public List<RecommendBean> getRecommendList(List<String> list){
		List<RecommendBean> recommendlist=new ArrayList<RecommendBean>();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		for(int i=0;i<list.size();i++){
			try{
				conn=JDBC_Connection.getConnection();
				String sql="select food.*,store_info.*"+
						" from food,sales,store_info"+
						" where food.food_ID=sales.food_ID"+
						" and sales.store_ID=store_info.store_ID"+
						" and food.food_ID= ?";
				pstm=conn.prepareStatement(sql);
				pstm.setInt(1, Integer.parseInt(list.get(i)));
				rs=pstm.executeQuery();
				while(rs.next()){
					RecommendBean recommendbean=new RecommendBean();
					recommendbean.setFood_ID(rs.getString("food_ID"));
					recommendbean.setFood_name(rs.getString("name"));
					recommendbean.setFood_price(rs.getString("price"));
					recommendbean.setSales_volume(rs.getString("sales_volume"));
					recommendbean.setStore_ID(rs.getString("store_ID"));
					recommendbean.setStore_name(rs.getString("store_name"));
					recommendbean.setStore_state(rs.getString("state"));
					
					recommendlist.add(recommendbean);
					
				}
			}catch(SQLException e){
				System.out.println("有异常！");
				e.printStackTrace();
			}
			JDBC_Connection.free(rs, conn, pstm);
		}
		return recommendlist;
	}
	
}
