import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletTest02 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		
		String id= req.getParameter("id");
		String pass= req.getParameter("pw");
		String id_rem= req.getParameter("id_rem");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println(id+ "("+ pass +") Login Success!");
		out.println("</h2></center></body></html>");
		out.println("");
		
		if(id_rem !=null && id_rem.equals("chk")){//chk표시가 되면 
			Cookie id_cookie = new Cookie("id", java.net.URLEncoder.encode(id, "UTF-8"));//쿠키생성
			id_cookie.setComment("Id saved!");//쿠키 코멘트
			id_cookie.setPath("/");//쿠키저장위치(루트=모든웹페이지에서 쿠키정보를 사용할수있도록 허용!)
			id_cookie.setMaxAge(60 * 60 *24 *365);//쿠키의 수명 1년
			resp.addCookie(id_cookie);//쿠키 저장하라!
			
			}
				
	}

}
