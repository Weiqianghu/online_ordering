package com.cn.store;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

//import org.apache.catalina.core.ApplicationPart;

import com.cn.imgcompress.ImgCompress;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class UploadTouxiangServlet
 */
@WebServlet("/UploadTouxiangServlet")
@MultipartConfig(location="c:/temp")
public class UploadTouxiangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 List piclist=new ArrayList();  //放上传的图片名
     
	    public void destroy() {
	        super.destroy(); // Just puts "destroy" string in log
	        // Put your code here
	    }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadTouxiangServlet() {
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
		response.setContentType("text/html;charset=gbk");
		PrintWriter out=response.getWriter();
		String path=this.getServletContext().getRealPath("/");
		HttpSession session=request.getSession();
		Part p=request.getPart("file1");
		if(p.getContentType().contains("image")){
			int path_id=path.indexOf("\\");
		    path=path.substring(0, path_id);
		    path=path+"\\Users\\Administrator\\workspace\\online_ordering\\WebContent";
			p.write(path+"/data/touxiang/"+(String)session.getAttribute("username")+".jpg");
			ImgCompress imgCom = new ImgCompress(path+"/data/touxiang/"+(String)session.getAttribute("username")+".jpg");
			imgCom.resizeFix(100, 100,path+"/data/touxiang/"+(String)session.getAttribute("username")+".jpg");
			response.sendRedirect("StoreInfoServlet");
		}
		else{
			out.write("请选择图片文件！！");
		}
	}
}
