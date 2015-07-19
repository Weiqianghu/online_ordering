package com.cn.users;

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

import com.cn.jdbc.*;
/**
 * Servlet implementation class UpdateUserinfoServlet
 */
@WebServlet("/UpdateUserinfoServlet")
public class UpdateUserinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserinfoServlet() {
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
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		String name=request.getParameter("name");
		String telephone=request.getParameter("telephone");
		double balance=Double.valueOf(request.getParameter("balance"));
		String address=request.getParameter("address");
		String qq=request.getParameter("qq");
		String email=request.getParameter("email");
		
		try{
			conn=JDBC_Connection.getConnection();
			String sql="update user_info set name= ?,telephone= ?,address=?,qq=?,email=?,balance=? where username = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, telephone);
			pstm.setString(3, address);
			pstm.setString(4, qq);
			pstm.setString(5, email);
			pstm.setDouble(6, balance);
			pstm.setString(7, username);
			pstm.executeUpdate();
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		response.sendRedirect("UserPageServlet");
	}

}
