package com.cn.store;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class AddStoreServlet
 */
@WebServlet("/AddStoreServlet")
public class AddStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter writrer=response.getWriter();
		this.doPost(request, response);
		writrer.flush();
		writrer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/json;charset=UTF-8");
		response.setStatus(200);
		String store_ID=request.getParameter("store_ID");
		String store_password=request.getParameter("store_password");
		String store_name=request.getParameter("store_name");
		String taste=request.getParameter("taste");
		String state="ÐÝÏ¢ÖÐ";
		String store_telephone=request.getParameter("store_telephone");
		System.out.println("store_telephone: "+store_telephone);
		String lowest_consume=request.getParameter("price");
		int order_number=0;
		
		StoreBean storebean=new StoreBean();
		storebean.setstore_ID(store_ID);
		storebean.setstore_name(store_name);
		storebean.settaste(taste);
		storebean.setstate(state);
		storebean.setlowest_consume(lowest_consume);
		storebean.setorder_number(order_number);
		storebean.setstore_password(store_password);
		storebean.setStore_telephone(store_telephone);
		AddStore addstore=new AddStore();
		int res=addstore.addstore(storebean);
		PrintWriter writrer=response.getWriter();
		if(res!=0){
			writrer.write("{\"reg\":\"ok\"}");
			}
		else{
			writrer.write("{\"reg\":\"error\"}");}
		writrer.flush();
		writrer.close();
	}

}
