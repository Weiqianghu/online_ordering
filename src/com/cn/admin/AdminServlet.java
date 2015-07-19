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

import com.cn.store.StoreBean;
import com.cn.users.UsersVo;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		HttpSession session =  request.getSession();
		if(session.getAttribute("username")==null){
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		else{
			AdminUser adminuser=new AdminUser();
			String whichone=request.getParameter("whichone");
			List<UsersVo> user_list=adminuser.showuserinfo();
			AdminStore adminstore=new AdminStore();
			List<StoreBean> store_list=adminstore.showstoreinfo();
			request.setAttribute("user_list", user_list);
			request.setAttribute("store_list", store_list);
			request.getRequestDispatcher("admin_index.jsp?from="+whichone).forward(request,response);
		}
	}

}
