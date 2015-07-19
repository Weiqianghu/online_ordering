package com.cn.food;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.cn.imgcompress.ImgCompress;
import com.cn.jdbc.JDBC_Connection;

/**
 * Servlet implementation class AddFoodServlet
 */
@WebServlet("/AddFoodServlet")
@MultipartConfig(location="c:/temp")
public class AddFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFoodServlet() {
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
		String name=request.getParameter("food_name");
		String price=request.getParameter("food_price");
		String food_taste=request.getParameter("food_taste");
		String introduction=request.getParameter("introduction");
				
		foodBean foodbean=new foodBean();
		foodbean.setName(name);
		foodbean.setPrice(price);
		foodbean.setSales_volume(0);
		foodbean.setFood_taste(food_taste);
		foodbean.setIntroduction(introduction);
		HttpSession session =  request.getSession();
		String store_ID=(String)session.getAttribute("username");
		
		foodbean.setStore_ID(store_ID);
		
		AddFood addfood=new AddFood();
		int res=addfood.addfood(foodbean);
		
		int food_ID=0;
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try{
			conn=JDBC_Connection.getConnection();
			String sql="select food.food_ID from sales,food where sales.food_ID=food.food_ID and food.name= ? and sales.store_ID= ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,foodbean.getName());
			pstm.setString(2,foodbean.getStore_ID());
			rs=pstm.executeQuery();
			while(rs.next()){
				food_ID=rs.getInt(1);
			}
		}catch(SQLException e){
			System.out.println("有异常！");
			e.printStackTrace();
		}
		
		PrintWriter out=response.getWriter();
		String path=this.getServletContext().getRealPath("/");
		Part p=request.getPart("tp");
		if(p.getContentType().contains("image")){
			int path_id=path.indexOf("\\");
		    path=path.substring(0, path_id);
		    path=path+"\\Users\\Administrator\\workspace\\online_ordering\\WebContent";
			p.write(path+"/data/foodpicture/"+food_ID+".jpg");
			ImgCompress imgCom = new ImgCompress(path+"/data/foodpicture/"+food_ID+".jpg");
			imgCom.resizeFix(220,220,path+"/data/foodpicture/"+food_ID+".jpg");
		}
		else{
			out.write("请选择图片文件！！");
		}
		
		PrintWriter writrer=response.getWriter();
		if(res!=0){
			//request.getRequestDispatcher("StoreFoodInfoServlet").forward(request,response);
			response.sendRedirect("StoreFoodInfoServlet");
			}
		else{
			//writrer.write("{\"reg\":\"error\"}");}
			writrer.write("出错了！");}
		writrer.flush();
		writrer.close();
	}

}
