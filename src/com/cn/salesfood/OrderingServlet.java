package com.cn.salesfood;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OrderingServlet
 */
@WebServlet("/OrderingServlet")
public class OrderingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderingServlet() {
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
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/json;charset=UTF-8");
		response.setStatus(200);
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		String totalprice=request.getParameter("totalprice");
		String telephone=request.getParameter("telephone");
		String address=request.getParameter("address");
		String basket_ID=request.getParameter("basket_ID");
		String store_ID=request.getParameter("store_ID");

		List<String> basket_ID_list=Arrays.asList(basket_ID.split(","));
		OrderingBean orderingbean=new OrderingBean();
		orderingbean.setUsername(username);
		orderingbean.setTelephone(telephone);
		orderingbean.setAddress(address);
		orderingbean.setPrice(totalprice);
		orderingbean.setStore_ID(store_ID);
		Ordering ordering=new Ordering();
		int res=ordering.ordering(orderingbean,basket_ID_list);
		
		PrintWriter writrer=response.getWriter();
		if(res==-1){
			out.write("{\"res\":\"balanceshort\"}");
		}
		else if(res==-2){
			out.write("{\"res\":\"less\"}");
		}
		else if(res>0){
			out.write("{\"res\":\"ok\"}");
			}
		else{
			out.write("{\"res\":\"error\"}");	
		}
		writrer.flush();
		writrer.close();
	}

}
