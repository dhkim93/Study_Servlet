import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IDCheck extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = "";

		//C:\Users/user\AppData\Roaming\Microsoft\Windows\Cookies 쿠키저장위치
		Cookie[] cookies = req.getCookies();//클라이언트에 있는 쿠키정보를 가져옴

		for (int i = 0; cookies != null && i < cookies.length; i++) {
			String key = cookies[i].getName();//i번째 쿠키의 이름저장
			if (key.equals("id")) {//쿠키이름이 id와같으면
				id = cookies[i].getValue();//쿠키의 값을가져옴
				id = java.net.URLDecoder.decode(id, "UTF-8");//디코딩
			}

		}
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		out.println("<html><body>");
		out.println("<form method='post' action='Servlet02'>");
		out.println("<table border=1>");
		out.println("<tr>");
		out.println("<td align='right' width='30%'>ID : </td>");
		out.println("<td align='center'><input type='text' name='id' value='"
				+ id + "' size='10'/></td>");
		out.println("<td align='center'><input type='submit'value='로그인'/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td align='right'>PASS : </td>");
		out.println("<td align='center'><input type='password' name='pw' size='9'/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='3'><input type='checkbox' name='id_rem' value='chk'/>아이디 기억하기 체크!");
		out.println("</tr></table>");
		out.println("</form>");
		out.println("</body></html>");
		// out.close();
	}

}
