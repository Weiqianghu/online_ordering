package com.cn.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.mail.MailSenderInfo;
import com.util.mail.SimpleMailSender;
import com.util.mail.mailvalidate;

/**
 * Servlet implementation class ValidateEmailServlet
 */
@WebServlet("/ValidateEmailServlet")
public class ValidateEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateEmailServlet() {
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
		ExistEmail existemail=new ExistEmail();
		int res=existemail.existemail(email);
		ValidatAassist validataassist=new ValidatAassist();
		boolean isemail=validataassist.checkMail(email);
		if(res==0&&isemail==true){
			String testcode=validataassist.getStringRandom(10);
			mailvalidate validate=new mailvalidate();
			ValidateEmail validateemail=new ValidateEmail();
			validateemail.insertTestCode(email,testcode);
			validate.validate(email,testcode);
			out.write("ok");
		}
		else
			out.write("exist");
		out.flush();
		out.close();
		
	}
}
