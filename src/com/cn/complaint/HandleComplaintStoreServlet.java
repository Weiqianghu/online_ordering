package com.cn.complaint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HandleComplaintStoreServlet
 */
@WebServlet("/HandleComplaintStoreServlet")
public class HandleComplaintStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleComplaintStoreServlet() {
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
		int ID=Integer.parseInt(request.getParameter("ID"));
		String to_who=request.getParameter("to_who");
		String whichone=request.getParameter("whichone");
		HandleComplaintStore handlecomplaintstore=new HandleComplaintStore();
		handlecomplaintstore.handlecomplaintstore(ID, to_who);
		request.getRequestDispatcher("ShowComplaintStoreServlet?whichone="+whichone).forward(request, response);
	}

}
