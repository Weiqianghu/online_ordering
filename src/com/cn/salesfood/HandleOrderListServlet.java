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
 * Servlet implementation class HandleOrderListServlet
 */
@WebServlet("/HandleOrderListServlet")
public class HandleOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleOrderListServlet() {
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
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String store_ID=(String)session.getAttribute("username");
		String username=request.getParameter("username");
		Double total_price=Double.valueOf(request.getParameter("total_price"));
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
		 String order_state="null";
		 int state=1;
		 try{
			    conn=JDBC_Connection.getConnection();
				String sql="select * from ordering,sales,store_info where ordering.sales_ID=sales.ID and sales.store_ID=store_info.store_ID and username = ? and order_time= ? and store_info.store_ID= ?";
				pstm=conn.prepareStatement(sql);
				pstm.setString(1, username);
				pstm.setTimestamp(2,order_time);
				pstm.setString(3,store_ID);
				rs=pstm.executeQuery();
				while(rs.next()){
					order_state=rs.getString("order_state");
				}
				if(order_state.equals("未处理")){
					state=1;
					sql="update ordering set order_state = '已处理' where ordering.username = ? and ordering.order_time= ?";
					pstm=conn.prepareStatement(sql);
					pstm.setString(1, username);
					pstm.setTimestamp(2,order_time);
					pstm.executeUpdate();
					
					sql="update store_info set balance=balance + ? where store_ID = ?";
					pstm=conn.prepareStatement(sql);
		     		pstm.setDouble(1,total_price );
		     		pstm.setString(2,store_ID);
		     		pstm.executeUpdate();
				}
				else if(order_state.equals("已处理")){
					state=2;
				}
				else
					state=0;
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 JDBC_Connection.free(rs, conn, pstm);
		 response.setContentType("text/json;charset=UTF-8");
			response.setStatus(200);
		 if(state==1){
			 out.write("{\"res\":\"ok\"}");
		 }
		 else if(state==0)
			 out.write("{\"res\":\"cancel\"}");
		 else
			 out.write("{\"res\":\"handle\"}");
		 out.flush();
		 out.close();
	}

}
