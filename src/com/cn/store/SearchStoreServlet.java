package com.cn.store;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cn.apriorialgorithm.AprRecommend;
import com.cn.jdbc.*;
/**
 * Servlet implementation class SearchStoreServlet
 */
@WebServlet("/SearchStoreServlet")
public class SearchStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStoreServlet() {
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
		SelectStore selectStore=new SelectStore();
		HttpSession session =  request.getSession();
		String item = request.getParameter("item");
		String content=request.getParameter("content");
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		String sql;
		List<StoreBean> list = new ArrayList<StoreBean>();
		if(item.equals("商铺名称"))
			sql="select * from store_info where store_name like ?";
		else if(item.equals("风味口味"))
			sql="select * from store_info where taste like ?";
		else sql="select * from store_info where state like ?"; 
		try{
			conn=JDBC_Connection.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, "%"+content+"%");
			rs=pstm.executeQuery();
			while(rs.next()){
				StoreBean storeBean=new StoreBean();
				storeBean.setstore_name(rs.getString("store_name"));
				storeBean.settaste(rs.getString("taste"));
				storeBean.setlowest_consume(rs.getString("lowest_consume"));
				storeBean.setorder_number(rs.getInt("order_number"));
				storeBean.setstate(rs.getString("state"));
				storeBean.setstore_ID(rs.getString("store_ID"));
				storeBean.setAddress(rs.getString("address"));
				storeBean.setLongitude(rs.getDouble("longitude"));
				storeBean.setLatitude(rs.getDouble("latitude"));
				
				String sql_score="select avg(score) from comment group by store_ID having store_ID=?";
				PreparedStatement pstmt=conn.prepareStatement(sql_score);
				pstmt.setString(1,rs.getString("store_ID"));
				ResultSet rst=pstmt.executeQuery();
				while(rst.next()){
					storeBean.setStore_score(Math.round(rst.getDouble("avg(score)")*10)/10.0);
				}
				
				list.add(storeBean);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("有异常！");
		}
		JDBC_Connection.free(rs, conn, pstm);
		Recommend recommend=new Recommend();
		List<RecommendBean> listrecommend=null;
	    if((String)session.getAttribute("whichone")!=null&&((String)session.getAttribute("whichone")).equals("user")){
			listrecommend=recommend.recommendfood((String)session.getAttribute("username"));
	    }
	    else
	    {
	    	listrecommend=recommend.irecommendfood();
	    }
	    
	    AprRecommend aprrecommend=new AprRecommend();
		List<RecommendBean> recommendlist=aprrecommend.aprrecommend();
		listrecommend.addAll(recommendlist);
		int supportcount=selectStore.getSupportCount();
		request.setAttribute("listrecommend", listrecommend);
		request.setAttribute("recommendlist", recommendlist);
		request.setAttribute("supportcount", supportcount);
		request.setAttribute("list", list);
		request.setAttribute("show", "none");
		request.getRequestDispatcher("index.jsp").forward(request,response);
	}

}
