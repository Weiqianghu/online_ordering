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
import javax.servlet.http.HttpSession;

import com.cn.jdbc.JDBC_Connection;

/**
 * Servlet implementation class AdminDeleteStoreServlet
 */
@WebServlet("/AdminDeleteStoreServlet")
public class AdminDeleteStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteStoreServlet() {
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
		String store_ID=request.getParameter("store_ID");
		String whichone=request.getParameter("whichone");
		try{
			conn=JDBC_Connection.getConnection();
			String sql="delete from store_login where store_ID = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, store_ID);
			pstm.executeUpdate();
		}catch(SQLException e){
			System.out.println("”–“Ï≥££°");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		response.sendRedirect("AdminServlet?whichone="+whichone);
	}
}
