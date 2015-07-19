package com.cn.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.jdbc.JDBC_Connection;
import com.cn.store.StoreBean;

public class ShowMonthOrderNum {
	public List<OrderNumBean> showmonthordernum(String year){
		List<OrderNumBean> list=new ArrayList<OrderNumBean>();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select DATE_FORMAT(order_time,'%Y-%m') as month,sum(num) as num"+  
                        " from ordering"+ 
                       // "Where DATE_FORMAT(DATE_ADD(now(), INTERVAL -1 YEAR),'%Y-%m-%d') <= date(order_time)"+
                        " where DATE_FORMAT(order_time,'%Y')= ?"+ 
                        " and ordering.order_state='已处理' "+
                        " group by month"+   
                        " order by month";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, year);
			rs=pstm.executeQuery();
			while(rs.next()){
				OrderNumBean ordernumbean=new OrderNumBean();
				ordernumbean.setTime(rs.getString("month"));
				ordernumbean.setOrder_num(rs.getString("num"));
	
				list.add(ordernumbean);
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return list;
	}

}
