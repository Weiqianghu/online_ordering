package com.cn.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.jdbc.*;
/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	String reg;
    public loginServlet() {
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
		boolean isshop=false;
		PrintWriter writrer=response.getWriter();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String whichone=request.getParameter("whichone");
		String remember=request.getParameter("remember");
		if(username.equals("admin")){
			whichone="admin";
		}
		if(remember.equals("true")){
			Cookie cookie =new Cookie("username", username);
			cookie.setMaxAge(3600*24*10);
			response.addCookie(cookie);
			cookie=new Cookie("password",password);
			cookie.setMaxAge(3600*24*10);
			response.addCookie(cookie);
			cookie=new Cookie("whichone", whichone);
			cookie.setMaxAge(3600*24*10);
			response.addCookie(cookie);
		}
		
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		String sql;
		try{
			conn=JDBC_Connection.getConnection();
			if(whichone.equals("user")){
			     sql="select * from user_login where username=? and password= ?" ;
			}
			else if(whichone.equals("shop"))
			{
				 sql="select * from store_login where store_ID=? and store_password= ?" ;
				 isshop=true;
			}
			else{
				sql="select * from admin_login where username=? and password= ?" ;
			}
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			rs=pstm.executeQuery();
			HttpSession session =  request.getSession();
			if(rs.next()){
				if(isshop==true){
					sql="select store_name from store_info where store_ID= ?";
					pstm=conn.prepareStatement(sql);
					pstm.setString(1, username);
					rs=pstm.executeQuery();
					if(rs.next()){
						session.setAttribute("store_name", rs.getString("store_name"));
					}
				}
				session.setAttribute("username", username);
				writrer.write("{\"login\":\"ok\"}");
				session.setAttribute("password", password);
				session.setAttribute("whichone", whichone);
			}
			else{
				writrer.write("{\"login\":\"error\"}");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		JDBC_Connection.free(rs, conn, pstm);
		response.setContentType("text/json;charset=UTF-8");
		response.setStatus(200);
		writrer.flush();
		writrer.close();
	}

}
