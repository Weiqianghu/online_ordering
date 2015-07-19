package com.cn.food;
import java.sql.*;

import javax.servlet.http.HttpSession;

import com.cn.jdbc.*;

public class AddFood {
	int response=0;
	public int addfood(foodBean foodbean){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			String sql="insert into food(name,price,sales_volume,introduction,food_taste) values(?,?,?,?,?)";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,foodbean.getName());
			pstm.setString(2,foodbean.getPrice());
			pstm.setInt(3,foodbean.getSales_volume());
			pstm.setString(4, foodbean.getIntroduction());
			pstm.setString(5, foodbean.getFood_taste());
			if(pstm.executeUpdate()==1){
				sql="SELECT max(food_ID) from food";
				pstm=conn.prepareStatement(sql);
				rs=pstm.executeQuery();
				while(rs.next()){
					foodbean.setFood_ID(rs.getInt(1));
				}
			}
			sql="insert into sales(food_ID,store_ID) values(?,?)";
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1,foodbean.getFood_ID());
			pstm.setString(2,foodbean.getStore_ID());
			response=pstm.executeUpdate();
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return response;
	}

	

}
