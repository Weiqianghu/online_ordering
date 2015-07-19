package com.cn.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.jdbc.JDBC_Connection;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		this.doPost(request, response);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json;charset=UTF-8");
		response.setStatus(200);
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		String store_ID=null;
		String content=request.getParameter("content");
		content=content.replace("<", "&lt;");
		content=content.replace(">", "&gt;");
		double score=Double.valueOf(request.getParameter("score"));
		boolean state=false;
		Timestamp order_time=new Timestamp(System.currentTimeMillis());
		 try{
		 order_time = Timestamp.valueOf(request.getParameter("order_time"));
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 Connection conn=null;
		 PreparedStatement pstm=null;
		 ResultSet rs=null;
		 try{
			 conn=JDBC_Connection.getConnection();
				String sql="select * from ordering,sales,store_info where ordering.sales_ID=sales.ID and sales.store_ID=store_info.store_ID and username = ? and order_time= ?";
				pstm=conn.prepareStatement(sql);
				pstm.setString(1, username);
				pstm.setTimestamp(2,order_time);
				rs=pstm.executeQuery();
				while(rs.next()){
					store_ID=rs.getString("store_ID");
				}
					java.util.Date date=new java.util.Date();
			    	Timestamp comment_time=new Timestamp(date.getTime());
			    	sql="insert into comment(content,comment_time,username,store_ID,score) values(?,?,?,?,?)";
			    	pstm=conn.prepareStatement(sql);
			    	pstm.setString(1,content);
			    	pstm.setTimestamp(2,comment_time);
			    	pstm.setString(3, username);
			    	pstm.setString(4, store_ID);
			    	pstm.setDouble(5, score);
			    	pstm.executeUpdate();
			    	state=true;
			 } 
		 catch(SQLException e){
				 e.printStackTrace();
		 }
		 JDBC_Connection.free(rs, conn, pstm);
		 if(state==true){
			 out.write("{\"res\":\"ok\"}");
		 }
		 else
		 {
			 out.write("{\"res\":\"notcomment\"}");
		 }
	}
}
