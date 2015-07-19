package com.cn.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.jdbc.*;

import java.sql.*;
/**
 * Servlet implementation class ExistsServlet
 */
@WebServlet("/ExistsServlet")
public class ExistsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExistsServlet() {
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
		String location=request.getParameter("location");
		String username=request.getParameter("username");
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		int resp=0;
		String sql;
		if(location.equals("/online_ordering/register.jsp")){
			sql="select * from user_login where username=?";
		}
		else
		{
			sql="select * from store_login where store_ID=?";
		}
		
		try{
			conn=JDBC_Connection.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			rs=pstm.executeQuery();
			while(rs.next())
				resp++;
		}catch(SQLException e){
			System.out.println("”–“Ï≥£!");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		if(resp==0)
			out.write("{\"res\":\"ok\"}");
		else
			out.write("{\"res\":\"exist\"}");
		out.flush();
		out.close();
	}

}
