package com.cn.store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cn.jdbc.*;

public class SelectStore {
	public List<StoreBean> querystore(String condition){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<StoreBean> list=new ArrayList<StoreBean>();
		try{
			conn=JDBC_Connection.getConnection();
			String sql;
			if(condition.equals("评分")){
				sql="select comment.score,store_info.store_ID,store_info.store_name,store_info.taste, "
						+ "store_info.lowest_consume,store_info.order_number, "
						+ "store_info.state,store_info.address, "
						+ "store_info.longitude,store_info.latitude,avg(score) "
						+ " from comment RIGHT JOIN store_info on comment.store_ID=store_info.store_ID  "
						+ " group by store_info.store_ID order by avg(score) desc limit 8";
			}
			else if(condition.equals("人气"))
			{
				sql="select * from ("+
					" select * from store_info "+
					" order by order_number desc"+
					" ) a"+
					" limit 8";
			}
			else
				{
				sql="select * from ("+
						" select * from store_info "+
						" order by state desc"+
						" ) a"+
						" limit 8";}
			
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				StoreBean storeBean=new StoreBean();
				storeBean.setstore_ID(rs.getString("store_ID"));
				storeBean.setstore_name(rs.getString("store_name"));
				storeBean.settaste(rs.getString("taste"));
				storeBean.setlowest_consume(rs.getString("lowest_consume"));
				storeBean.setorder_number(rs.getInt("order_number"));
				storeBean.setstate(rs.getString("state"));
				storeBean.setAddress(rs.getString("address"));
				storeBean.setLongitude(rs.getDouble("longitude"));
				storeBean.setLatitude(rs.getDouble("latitude"));
				
				String sql_score="select avg(score) from comment group by store_ID having store_ID=?";
				PreparedStatement pstmt=conn.prepareStatement(sql_score);
				pstmt.setString(1,rs.getString("store_ID"));
				ResultSet rst=pstmt.executeQuery();
				while(rst.next()){
					storeBean.setStore_score(Math.round(rst.getDouble("avg(score)")*10)/10.0);
				}

				list.add(storeBean);
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		return list;
	}
	public int getSupportCount(){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		int supportcount=0;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="SELECT count(username) FROM online_ordering.user_info where support='1'";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
				supportcount=rs.getInt(1);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return supportcount;
	}

}
