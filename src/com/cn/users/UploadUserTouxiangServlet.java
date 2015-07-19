package com.cn.users;

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

import com.cn.imgcompress.ImgCompress;

/**
 * Servlet implementation class UploadUserTouxiangServlet
 */
@WebServlet("/UploadUserTouxiangServlet")
@MultipartConfig(location="c:/temp")
public class UploadUserTouxiangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadUserTouxiangServlet() {
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
			p.write(path+"/data/usertouxiang/"+(String)session.getAttribute("username")+".jpg");
			ImgCompress imgCom = new ImgCompress(path+"/data/usertouxiang/"+(String)session.getAttribute("username")+".jpg");
			imgCom.resizeFix(100, 100,path+"/data/usertouxiang/"+(String)session.getAttribute("username")+".jpg");
			response.sendRedirect("UserPageServlet");
		}
		else{
			out.write("ÇëÑ¡ÔñÍ¼Æ¬ÎÄ¼þ£¡£¡");
		}
	}

}
