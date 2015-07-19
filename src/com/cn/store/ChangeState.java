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

import com.cn.jdbc.JDBC_Connection;

/**
 * Servlet implementation class ChangeState
 */
@WebServlet("/ChangeState")
public class ChangeState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeState() {
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
		String state=null;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select state from store_info where store_ID = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			rs=pstm.executeQuery();
			System.out.println("已经执行！");
			while(rs.next()){
				state=rs.getString("state");
			}
			
			if(state.equalsIgnoreCase("休息中"))
				sql="update store_info set state = '营业中' where store_ID=?";
			else
				sql="update store_info set state = '休息中' where store_ID=?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.executeUpdate();
			System.out.println("已经执行了！");
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		response.sendRedirect("StoreInfoServlet");
	}

}
