package com.cn.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
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
		HttpSession session =  request.getSession();
		int res=0;
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		UsersVo usersVo=new UsersVo();
		usersVo.setUsername(username);
		usersVo.setPassword(password);
		usersVo.setEmail(email);
		String istest=(String)session.getAttribute("istest");
		if(istest.equals("true")){
			AddUsers addusers=new AddUsers();
			res=addusers.addusers(usersVo);
		}
		PrintWriter writrer=response.getWriter();
		if(res!=0){
			writrer.write("{\"reg\":\"ok\"}");
			session.removeAttribute("istest");
			}
		else{
			writrer.write("{\"reg\":\"error\"}");
			}
		writrer.flush();
		writrer.close();
	}

}
