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
 * Servlet implementation class AdminExistsServlet
 */
@WebServlet("/AdminExistsServlet")
public class AdminExistsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminExistsServlet() {
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
		String whichone=request.getParameter("whichone");
		String username=request.getParameter("username");
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		int resp=0;
		String sql;
		if(whichone.equals("user")){
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
