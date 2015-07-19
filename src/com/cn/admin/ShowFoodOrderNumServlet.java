package com.cn.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowFoodOrderNumServlet
 */
@WebServlet("/ShowFoodOrderNumServlet")
public class ShowFoodOrderNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowFoodOrderNumServlet() {
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
		ShowFoodOrderNum showfoodordernum=new ShowFoodOrderNum();
		List<FoodOrderNumBean> list=showfoodordernum.showfoodordernum();
		request.setAttribute("foodordernumlist", list);
		
		HttpSession session =  request.getSession();
		if(session.getAttribute("username")==null){
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}else
		{
			request.getRequestDispatcher("admin_index.jsp").forward(request,response);
		}
	}

}
