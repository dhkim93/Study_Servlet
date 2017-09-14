import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletTest01 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name=req.getParameter("name");
		String tel=req.getParameter("tel");
		String addr=req.getParameter("addr");
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out= resp.getWriter();
		
		out.println("<html><head>");
		out.println("<style type='text/css'>");
		out.println(".n_border { border : none };");
		out.println("</style></head><body>");
		out.println("전송된 이름 :");
		out.println("<input type='text' class='n_border' value='" + name + "'/><br/>");
		out.println("");
		out.println("전송된 전화번호 :");
		out.println("<input type='text' class='n_border' value='" + tel + "'/><br/>");
		out.println("");
		out.println("전송된 주소 :");
		out.println("<input type='text' class='n_border' value='" + addr + "'/><br/>");
		out.println("</body></html>");
	}

}
