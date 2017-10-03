import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class JoinPage extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		
		resp.setContentType("text/html;charset=utf-8");
		
		ServletContext sevContext = this.getServletContext();
		RequestDispatcher disp = null;
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		PrintWriter out = resp.getWriter();
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		out.println("<html><body><center><h3>");
		
		if(id.equals("")){
			System.out.println("아이디입력해주세요");
			
			  out.println("<script type='text/javascript'>");
              out.println("alert('아이디가 정확하지 않습니다.');");
              out.println("history.back();");
              out.println("</script>");
              out.flush();			
		}else if(pw.equals("")){
			System.out.println("비밀번호입력해주세요");
			  out.println("<script type='text/javascript'>");
              out.println("alert('비밀번호가 정확하지 않습니다.');");
              out.println("history.back();");
              out.println("</script>");
              out.flush();
		}else{
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			String query = "insert into memberB values (null, ?, ?)";
			try {
				Context serverCon = new InitialContext();
				DataSource source = (DataSource) serverCon
						.lookup("java:comp/env/jdbc/myconn");
				conn = source.getConnection();
				System.out.println("서버연결성공!");
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				
				int res = pstmt.executeUpdate();
				
				out.println("회원가입성공!");
				disp = sevContext.getRequestDispatcher("/Main");
				disp.forward(req, resp);
				
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				out.println("SQL erro : " + e.getMessage());
			}
			
			out.println("</h3></center></body></html>");
		}
	}

}
