package com.cn.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowCommentServlet
 */
@WebServlet("/ShowCommentServlet")
public class ShowCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCommentServlet() {
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
		String store_ID=request.getParameter("store_ID");
		String store_name= java.net.URLDecoder.decode(request.getParameter("store_name"), "utf-8");
		String store_state= java.net.URLDecoder.decode(request.getParameter("store_state"), "utf-8");
		ShowComment showcomment=new ShowComment();
		List<CommentBean> comment_list=showcomment.showcomment(store_ID);
		request.setAttribute("comment_list", comment_list);
		request.setAttribute("store_ID", store_ID);
		request.setAttribute("store_name", store_name);
		request.setAttribute("store_state", store_state);
		request.getRequestDispatcher("enter_shop_comment.jsp").forward(request,response);
	}

}
