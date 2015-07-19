package com.cn.complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.cn.jdbc.JDBC_Connection;

public class HandleComplaintStore {
	@SuppressWarnings("resource")
	public boolean handlecomplaintstore(int ID,String handle){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		boolean ok=false;
		String username="";
		String store_ID="";
		int totalprice=0;
		String state="";
		String order_state="";
		Timestamp order_time=new Timestamp(System.currentTimeMillis());;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * "+
				" from complaint"+
				" where ID= ?  ";
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1, ID);
			rs=pstm.executeQuery();
			while(rs.next()){
				username=rs.getString("username");
				order_time = rs.getTimestamp("order_time");
				store_ID=rs.getString("store_ID");
				state=rs.getString("state");
			}
			if(!state.equals("未处理")){
				return false;
			}
			if(handle.equals("to_user")){
				conn=JDBC_Connection.getConnection();
				sql="SELECT * FROM ordering,sales,food"+
						" where ordering.sales_ID=sales.ID"+
						" and sales.food_ID=food.food_ID"+
						" and ordering.username= ? "+
						" and ordering.order_time= ?";
				pstm=conn.prepareStatement(sql);
				pstm.setString(1, username);
				pstm.setTimestamp(2,order_time);
				rs=pstm.executeQuery();
				while(rs.next()){
					totalprice=totalprice+rs.getInt("num")*rs.getInt("price");
					order_state=rs.getString("order_state");
					sql="update ordering set order_state= '已取消' where ID = ?";
					pstm=conn.prepareStatement(sql);
					pstm.setInt(1,rs.getInt("ordering.ID"));
					pstm.executeUpdate();
				}
				sql="update user_info set balance=balance + ? where username = ?";
				pstm=conn.prepareStatement(sql);
				pstm.setInt(1,totalprice);
				pstm.setString(2,username);
				pstm.executeUpdate();
			    if(order_state.equals("已处理")){
			    	sql="update store_info set balance=balance - ? where store_ID = ?";
			    	pstm=conn.prepareStatement(sql);
			    	pstm.setInt(1,totalprice);
			    	pstm.setString(2,store_ID);
			    	pstm.executeUpdate();
			    }
     		
				sql="update complaint set state= '已处理' where ID = ?";
				pstm=conn.prepareStatement(sql);
				pstm.setInt(1,ID);
				pstm.executeUpdate();
				ok=true;
			}
			else{
				sql="update complaint set state= '已驳回' where ID = ?";
				pstm=conn.prepareStatement(sql);
				pstm.setInt(1,ID);
				pstm.executeUpdate();
				ok=true;
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return ok;
	}

}
