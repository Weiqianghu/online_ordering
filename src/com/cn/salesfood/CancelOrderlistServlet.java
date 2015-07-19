package com.cn.salesfood;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.jdbc.JDBC_Connection;

/**
 * Servlet implementation class CancelOrderlistServlet
 */
@WebServlet("/CancelOrderlistServlet")
public class CancelOrderlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderlistServlet() {
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
		 String order_state=null;
		 int cancel=1;
		 try{
			    conn=JDBC_Connection.getConnection();
				String sql="select * from ordering,sales,store_info,food where ordering.sales_ID=sales.ID and sales.store_ID=store_info.store_ID and sales.food_ID=food.food_ID and username = ? and order_time= ?";
				pstm=conn.prepareStatement(sql);
				pstm.setString(1, username);
				pstm.setTimestamp(2,order_time);
				rs=pstm.executeQuery();
				while(rs.next()){
					order_state=rs.getString("order_state");
					if(order_state.equals("未处理")){
							sql="update store_info set balance=balance - ? where store_ID = ?";
							pstm=conn.prepareStatement(sql);
							pstm.setInt(1, rs.getInt("price")*rs.getInt("num"));
							pstm.setString(2,rs.getString(11));
						    pstm.executeUpdate();
						    sql="update user_info set balance=balance + ? where username = ?";
						     pstm=conn.prepareStatement(sql);
						     pstm.setInt(1, rs.getInt("price")*rs.getInt("num"));
						     pstm.setString(2,username);
						     pstm.executeUpdate();
					}
				}
				if(order_state.equals("未处理")){
					sql="update ordering set order_state = '已取消' where username = ? and order_time= ?";
					pstm=conn.prepareStatement(sql);
					pstm.setString(1, username);
					pstm.setTimestamp(2,order_time);
					pstm.executeUpdate();
				}
				else if(order_state.equals("已处理")){
					cancel=0;
				}
				else
					cancel=2;
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 JDBC_Connection.free(rs, conn, pstm);
		 if(cancel==1){
			 out.write("{\"res\":\"ok\"}");
		 }
		 else if(cancel==0)
			 out.write("{\"res\":\"handle\"}");
		 else
			 out.write("{\"res\":\"cancel\"}");
		 out.flush();
		 out.close();
	}

}
