package com.cn.salesfood;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.jdbc.JDBC_Connection;

/**
 * Servlet implementation class ShowBasketServlet
 */
@WebServlet("/ShowBasketServlet")
public class ShowBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBasketServlet() {
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
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username"),telephone=null,address=null,name=null;
		String store_ID=request.getParameter("store_ID");
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		double totalprice=0.0;
		List<OrderingBean> basket_list=new ArrayList<OrderingBean>();
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select * from basket,sales,food,store_info where basket.sales_ID=sales.ID and sales.food_ID=food.food_ID and sales.store_ID=store_info.store_ID and username = ? and store_info.store_ID = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, store_ID);
			rs=pstm.executeQuery();
			while(rs.next()){
				OrderingBean orderingbean=new OrderingBean();
				orderingbean.setBasket_ID(rs.getInt("ID"));
				orderingbean.setFood_ID(rs.getInt("food.food_ID"));
				orderingbean.setUsername(rs.getString("username"));
				orderingbean.setNum(rs.getInt("num"));
				orderingbean.setSales_ID(rs.getInt("sales_ID"));
				orderingbean.setName(rs.getString("name"));
				orderingbean.setPrice(rs.getString("price"));
				orderingbean.setStore_ID(rs.getString("store_info.store_ID"));
				totalprice=totalprice+Double.valueOf(rs.getString("price"))*rs.getInt("num");
				basket_list.add(orderingbean);
			}
			sql="select * from user_info where username = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, username);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				
				telephone=rs.getString("telephone");
				address=rs.getString("address");
				name=rs.getString("name");
			}
		}
		catch(SQLException e){
			System.out.println("”–“Ï≥£");
			e.printStackTrace();
		}
		request.setAttribute("basket_list",basket_list);
		request.setAttribute("totalprice",totalprice);
		request.setAttribute("telephone",telephone);
		request.setAttribute("name",name);
		request.setAttribute("store_ID",store_ID);
		request.setAttribute("address",address);
	    request.getRequestDispatcher("basket_index.jsp").forward(request,response);
	}

}
