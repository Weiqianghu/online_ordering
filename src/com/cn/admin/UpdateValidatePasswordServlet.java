package com.cn.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateValidatePasswordServlet
 */
@WebServlet("/UpdateValidatePasswordServlet")
public class UpdateValidatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateValidatePasswordServlet() {
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
		HttpSession session=request.getSession();
		String password=request.getParameter("password");
		String email=(request.getParameter("email"));
		String istest=(String) session.getAttribute("istest");
		PrintWriter out=response.getWriter();
		if((session.getAttribute("istest")!= null)&&(istest.equals("true"))){
			UpdatePassword updatepassword=new UpdatePassword();
			if(updatepassword.updatepassword(email, password)){
				out.write("success");
				session.removeAttribute("istest");
			}
		}
		else
			out.write("false");
	}

}
