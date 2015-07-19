package com.cn.store;

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
 * Servlet implementation class UpdateStoreInfoServlet
 */
@WebServlet("/UpdateStoreInfoServlet")
public class UpdateStoreInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStoreInfoServlet() {
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
		HttpSession session =request.getSession();
		String store_ID=(String)session.getAttribute("username");
		 String store_name=request.getParameter("new_shop_name");
		 String taste=request.getParameter("new_shop_taste");
		 String lowest_consume=request.getParameter("new_shop_price");
		 String store_telephone=request.getParameter("new_shop_telephone");
		 double longitude=Double.parseDouble(request.getParameter("longitude")); 
		 double latitude=Double.parseDouble(request.getParameter("latitude"));
		 String address=request.getParameter("new_shop_address");
		 try{
				conn=JDBC_Connection.getConnection();
				String sql="update store_info set store_name= ?,taste= ?,lowest_consume= ?,store_telephone = ? ,longitude= ? ,latitude = ? ,address = ? where store_ID = ?";
				pstm=conn.prepareStatement(sql);
				pstm.setString(1, store_name);
				pstm.setString(2, taste);
				pstm.setString(3, lowest_consume);
				pstm.setString(4, store_telephone);
				pstm.setDouble(5, longitude);
				pstm.setDouble(6, latitude);
				pstm.setString(7, address);
				pstm.setString(8, store_ID);
				pstm.executeUpdate();
			}catch(SQLException e){
				System.out.println("”–“Ï≥££°");
				e.printStackTrace();
			}
		 JDBC_Connection.free(rs, conn, pstm);
		 response.sendRedirect("StoreInfoServlet");
	}

}
