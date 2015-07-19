package com.cn.salesfood;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cn.food.foodBean;
import com.cn.jdbc.*;

public class StoreFood {
	public List<foodBean> foodinfo(String store_ID){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<foodBean> list=new ArrayList<foodBean>();
		
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * from store_info,sales,food where store_info.store_ID = sales.store_ID and sales.food_ID=food.food_ID and store_info.store_ID =?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, store_ID);
			rs=pstm.executeQuery();
			while(rs.next()){
				foodBean foodbean=new foodBean();
				foodbean.setFood_ID(rs.getInt("food_ID"));
				foodbean.setName(rs.getString("name"));
				foodbean.setPrice(rs.getString("price"));
				foodbean.setSales_volume(rs.getInt("sales_volume"));
				foodbean.setFood_taste(rs.getString("food_taste"));
				foodbean.setIntroduction(rs.getString("introduction"));
				list.add(foodbean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return list;
	}

}
