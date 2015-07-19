package com.cn.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.jdbc.JDBC_Connection;

public class ShowFoodOrderNum {
	public List<FoodOrderNumBean> showfoodordernum(){
		List<FoodOrderNumBean> list=new ArrayList<FoodOrderNumBean>();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="SELECT sum(num),order_time,food.food_ID,ordering.ID,food.name"+
						" FROM ordering,sales,food "+
						//" WHERE DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(order_time)"+
						" where ordering.sales_ID=sales.ID "+
						" and sales.food_ID=food.food_ID "+
						" and ordering.order_state='已处理' "+
						" group by food.food_ID "+
						" order by sum(num) desc limit 10";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				FoodOrderNumBean foodOrdernumbean=new FoodOrderNumBean();
				foodOrdernumbean.setFood_name(rs.getString("name"));
				foodOrdernumbean.setOrder_num(rs.getString("sum(num)"));
				list.add(foodOrdernumbean);
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return list;
	}

}
