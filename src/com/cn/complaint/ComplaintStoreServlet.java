package com.cn.complaint;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ComplaintStoreServlet
 */
@WebServlet("/ComplaintStoreServlet")
public class ComplaintStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplaintStoreServlet() {
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
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		boolean exist=false;
		String username=(String)session.getAttribute("username");
		Timestamp order_time=new Timestamp(System.currentTimeMillis());
		 try{
		     order_time = Timestamp.valueOf(request.getParameter("order_time"));
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 String complaint_reason=request.getParameter("complaint_reason");
		 String user_telephone=request.getParameter("user_telephone");
		 ExistComplaint existcomplaint=new ExistComplaint();
		 exist=existcomplaint.existcomplaint(username, order_time);
		 if(exist==false){
			 ComplaintStore complaintstore=new ComplaintStore();
			 boolean ok=complaintstore.complaint(username, order_time, complaint_reason,user_telephone);
			 if(ok==true){
				 out.write("ok");
			 }
			 else
				 out.write("fail");
		 }
		 else
			 out.write("exist");
	}

}
