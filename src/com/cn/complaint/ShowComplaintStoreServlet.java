package com.cn.complaint;

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
 * Servlet implementation class ShowComplaintStoreServlet
 */
@WebServlet("/ShowComplaintStoreServlet")
public class ShowComplaintStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowComplaintStoreServlet() {
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
		String whichone=request.getParameter("whichone");
		ShowComplaintStore showcomplaintstore=new ShowComplaintStore();
		List<ComplaintStoreBean> list=showcomplaintstore.showcomplaintstore();
		request.setAttribute("list", list);
		HttpSession session =  request.getSession();
		if(session.getAttribute("username")==null){
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}else{
			request.getRequestDispatcher("admin_index.jsp?from="+whichone).forward(request, response);
		}
	}

}
