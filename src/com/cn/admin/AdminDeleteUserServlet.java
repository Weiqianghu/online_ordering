package com.cn.admin;

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

import com.cn.jdbc.JDBC_Connection;

/**
 * Servlet implementation class AdminDeleteUserServlet
 */
@WebServlet("/AdminDeleteUserServlet")
public class AdminDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteUserServlet() {
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
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		String whichone=request.getParameter("whichone");
		String username=request.getParameter("username");
		try{
			conn=JDBC_Connection.getConnection();
			String sql="delete from user_login where username = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.executeUpdate();
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		response.sendRedirect("AdminServlet?whichone="+whichone);
	}

}
