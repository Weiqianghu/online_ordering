package com.cn.salesfood;

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

/**
 * Servlet implementation class StoreOrderListServlet
 */
@WebServlet("/StoreOrderListServlet")
public class StoreOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreOrderListServlet() {
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
		HttpSession session=request.getSession();
		String store_ID=(String)session.getAttribute("username");
		StoreBean storebean=new StoreBean();
		storebean.setstore_ID(store_ID);
		StoreOrderList storeorderlist=new StoreOrderList();
		List<UserOrderListBean> storeorder_list=storeorderlist.showstoreorderlist(storebean);
		request.setAttribute("storeorder_list", storeorder_list);
		request.getRequestDispatcher("shop_order.jsp").forward(request,response);
	}

}
