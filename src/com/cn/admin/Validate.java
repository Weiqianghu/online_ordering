package com.cn.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.cn.jdbc.JDBC_Connection;

public class Validate {
	public int validate(String email,String testcode){
		Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.HOUR_OF_DAY,-1);
   	    Timestamp nowtime=new Timestamp(calendar.getTimeInMillis());
   	    Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * from validate where email= ? and testcode= ? order by sendtime desc limit 1";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, email);
			pstm.setString(2, testcode);
			rs=pstm.executeQuery();
			while(rs.next()){
				//if(!nowtime.after(rs.getTimestamp("sendtime"))){
					return 1;
				//}
			}
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return 0;
	}

}
