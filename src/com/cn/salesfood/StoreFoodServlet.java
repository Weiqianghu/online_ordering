package com.cn.salesfood;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cn.food.foodBean;

/**
 * Servlet implementation class StoreFoodServlet
 */
@WebServlet("/StoreFoodServlet")
public class StoreFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreFoodServlet() {
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
		String store_name=request.getParameter("store_name");
		String store_state=request.getParameter("store_state");
		request.setAttribute("store_name", store_name);
		request.setAttribute("store_state", store_state);
		StoreFood storefood=new StoreFood();
		List<foodBean> list=storefood.foodinfo(store_ID);
		request.setAttribute("list_shangpin", list);
		request.setAttribute("store_state", store_state);
		request.setAttribute("store_ID", store_ID);
		request.getRequestDispatcher("enter_shop.jsp").forward(request,response);
	}
}
