package mail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MailSendServlet
 */

public class MailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailSendServlet() {
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
		response.setContentType("text/json;charset=UTF-8");
		response.setStatus(200);
		String name=request.getParameter("name");
		String emailAddress=request.getParameter("emailAddress");
		String content=request.getParameter("content");
		boolean isSuccess=false;
		MailSend mailSend=new MailSend();
		if(name!=null&&!name.equals(" ")&&emailAddress!=null&&!emailAddress.equals(" ")&&content!=null&&!content.equals(" "))
			isSuccess=mailSend.mailSend(name, emailAddress, content);
		
		PrintWriter writrer=response.getWriter();
		if(isSuccess==true){
			writrer.write("{\"reg\":\"success\"}");
			}
		else{
			writrer.write("{\"reg\":\"fail\"}");
			}
		writrer.flush();
		writrer.close();
	}

}
