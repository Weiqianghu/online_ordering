package com.cn.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.apriorialgorithm.AprRecommend;
import com.cn.jdbc.JDBC_Connection;

/**
 * Servlet implementation class SelectStoreServlet
 */
@WebServlet("/SelectStoreServlet")
public class SelectStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectStoreServlet() {
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session =  request.getSession();
		if(session.getAttribute("username")==null){
			String username=null;
			String password=null;
			String whichone=null;
			String store_name=null;
			
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for(Cookie cookie: cookies)
				{
					if(cookie.getName().equals("username"))
					{
						username=cookie.getValue();
						//session.setAttribute("username", username);
					}
					if(cookie.getName().equals("password"))
					{
						password=cookie.getValue();
						//session.setAttribute("password", password);
					}
			    
					if(cookie.getName().equals("whichone"))
					{
						whichone=cookie.getValue();
						//session.setAttribute("whichone", whichone);
						if(whichone.equals("shop")){
							Connection conn=null;
							PreparedStatement pstm=null;
							ResultSet rs=null;
							conn=JDBC_Connection.getConnection();
							String sql="select store_name from store_info where store_ID= ?";
							try {
								pstm=conn.prepareStatement(sql);
								pstm.setString(1, username);
								rs=pstm.executeQuery();
								if(rs.next()){
									store_name=rs.getString("store_name");
									//session.setAttribute("store_name",store_name);
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
					Verification verification=new Verification();
					if(whichone!=null&&username!=null&&password!=null&&verification.verification(whichone, username, password)==1){
						session.setAttribute("username", username);
						session.setAttribute("password", password);
						session.setAttribute("whichone", whichone);
						session.setAttribute("store_name",store_name);
					}
				}
			}
		}
		String condition=request.getParameter("condition");
		if(condition==null){
			condition="д╛хо";
		}
		SelectStore selectStore=new SelectStore();
		List<StoreBean> list=selectStore.querystore(condition);
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
		request.setAttribute("list", list);
		request.setAttribute("supportcount", supportcount);
		request.setAttribute("show", "block");
		request.getRequestDispatcher("index.jsp").forward(request,response);
	}

}
