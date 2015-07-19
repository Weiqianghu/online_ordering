package com.cn.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.cn.jdbc.JDBC_Connection;
public class ShowComment {
	public List<CommentBean> showcomment(String store_ID){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<CommentBean> comment_list=new ArrayList<CommentBean>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * from comment where store_ID= ? order by comment_time  desc";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, store_ID);
			rs=pstm.executeQuery();
			while(rs.next()){
				CommentBean commentbean=new CommentBean();
				commentbean.setID(rs.getInt("ID"));
				commentbean.setComment_time(df.format(rs.getTimestamp("comment_time")));
				commentbean.setUsername(rs.getString("username"));
				commentbean.setStore_ID(rs.getString("store_ID"));
				commentbean.setContent(rs.getString("content"));
				commentbean.setScore(rs.getDouble("score"));
				comment_list.add(commentbean);
			}
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		return comment_list;
	}

}
