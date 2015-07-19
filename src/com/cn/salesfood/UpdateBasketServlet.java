package com.cn.salesfood;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.jdbc.JDBC_Connection;

/**
 * Servlet implementation class UpdateBasketServlet
 */
@WebServlet("/UpdateBasketServlet")
public class UpdateBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBasketServlet() {
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
		int basket_ID=Integer.parseInt(request.getParameter("basket_ID"));
		int num=Integer.parseInt(request.getParameter("num"));
		String store_ID=request.getParameter("store_ID");
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="update basket set num = ? where ID= ?";
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1, num);
			pstm.setInt(2, basket_ID);
			pstm.executeUpdate();
		}
		catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		request.getRequestDispatcher("ShowBasketServlet?store_ID="+store_ID).forward(request,response);
	}

}
