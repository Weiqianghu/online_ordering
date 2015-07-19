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
 * Servlet implementation class AdminUpdateStoreServlet
 */
@WebServlet("/AdminUpdateStoreServlet")
public class AdminUpdateStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateStoreServlet() {
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
		String newstore_ID=request.getParameter("newstore_ID");
		String store_password=request.getParameter("password");
		try{
			conn=JDBC_Connection.getConnection();
			String sql="update store_login set store_ID =?,store_password= ?  where store_ID = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, newstore_ID);
			pstm.setString(2, store_password);
			pstm.setString(3, store_ID);
			pstm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
	 response.sendRedirect("AdminServlet?whichone=shop");
	}

}
