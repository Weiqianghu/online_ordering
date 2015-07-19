package com.cn.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.mail.mailvalidate;

/**
 * Servlet implementation class ValidateUpdatePasswordServlet
 */
@WebServlet("/ValidateUpdatePasswordServlet")
public class ValidateUpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateUpdatePasswordServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		ValidatAassist validataassist=new ValidatAassist();
		String testcode=validataassist.getStringRandom(10);
		boolean isemail=validataassist.checkMail(email);
		if(isemail){
			mailvalidate validate=new mailvalidate();
			ValidateEmail validateemail=new ValidateEmail();
			validateemail.insertTestCode(email,testcode);
			validate.validate(email,testcode);
			out.write("ok");
		}
		else
			out.write("fail");
		out.flush();
		out.close();
	}
}
