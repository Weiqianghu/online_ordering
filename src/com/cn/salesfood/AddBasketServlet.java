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
 * Servlet implementation class AddBasketServlet
 */
@WebServlet("/AddBasketServlet")
public class AddBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBasketServlet() {
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
	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		String username=(String)session.getAttribute("username");
		int oldnum=0,num=1;
		String store_ID=request.getParameter("store_ID");
		String store_name=request.getParameter("store_name");
		int food_ID=Integer.parseInt(request.getParameter("food_ID"));
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		int sales_ID=0;
		boolean isnew=true;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * from sales where food_ID= ? and store_ID= ?";
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1,food_ID);
			pstm.setString(2,store_ID);
			rs=pstm.executeQuery();
			while(rs.next()){
				sales_ID=rs.getInt("ID");
			}
			sql="select * from basket where username = ? and sales_ID= ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,username);
			pstm.setInt(2,sales_ID);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				oldnum=rs.getInt("num");
				isnew=false;
			}
			if(isnew==false){
			sql="update basket set num = ? where username= ? and sales_ID= ? ";
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1,oldnum+ num);
			pstm.setString(2,username);
			pstm.setInt(3,sales_ID);
			pstm.executeUpdate();}
			else
			{
			sql="insert into basket(username,num,sales_ID) values(?,?,?)";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setInt(2, num);
			pstm.setInt(3,sales_ID);
			pstm.executeUpdate();
			}
		}
		catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		if(username==null)
			//response.sendRedirect("login.jsp");
			out.write("error");
		else
			out.write("ok");
		    //response.sendRedirect("StoreFoodServlet?store_ID="+java.net.URLEncoder.encode(store_ID,"UTF-8")+"&store_name="+ java.net.URLEncoder.encode(store_name,"UTF-8")+"&store_state="+java.net.URLEncoder.encode("营业中","UTF-8"));
	}

}
