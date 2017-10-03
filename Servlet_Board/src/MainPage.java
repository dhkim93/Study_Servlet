import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class MainPage extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		
//		String btnLogin = req.getParameter("Login");
		int chkId = 0;
		int chkPw = 0;
		boolean matchId = false;
		boolean matchPw = false;
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String idS ="";
		String pwS ="";
		
		System.out.println("@@@@@@@@@@@@@@@################");
		
		ServletContext sevContext = this.getServletContext();
		RequestDispatcher disp = null;
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body><center><h3>");

	      Connection conn = null;
	      PreparedStatement pstmt = null;
		String queryS = "select * from memberB";
//		System.out.println("BtnLogin : "+btnLogin);
//		System.out.println("BtnJoin : "+btnJoin);
	/*	if(btnJoin.equals("Join")){
			disp = sevContext.getRequestDispatcher("/Join");
			disp.forward(req, resp);

		}*/
//		 if(btnLogin.equals("Login")){
			try{
				System.out.println("Login 버튼 눌림!");
				Context context = new InitialContext();
		         DataSource source = (DataSource)context.lookup("java:comp/env/jdbc/myconn");
		         conn = source.getConnection();
			} catch (Exception e) {
		         e.printStackTrace();
		      }
			
			try{
				System.out.println("Connect Complete!");
				 pstmt = conn.prepareStatement(queryS);
		           ResultSet rs = pstmt.executeQuery();
		           
		           while(rs.next()){
		        	   idS = rs.getString("id");
		        	   pwS = rs.getString("pw");
		        	   
		        	   System.out.println("서버 Id : "+idS+",   서버PW : "+pwS);
		        	   System.out.println("입력ID : "+id+", 입력PW : "+pw);
		        	  
		        	   if(idS.equals(id)){
		        		   ++chkId;
		        	   }
		        	   if(pwS.equals(pw)){
		        		   ++chkPw;
		        	   }
		           }
		           if(chkId>=1){
		        	   matchId = true;
		        	   if(chkPw>=1){
		        		   matchPw = true;
		        		   System.out.println("Login 성공!!");
		        		   disp = sevContext.getRequestDispatcher("/Servlet02");
		        		   disp.forward(req, resp);
		        	   }else{
		        		   out.println("비번 ERROR!");
		        	   }
		           }else{
		        	   out.println("아이디 ERROR!");
		           }
		           pstmt.close();
			       conn.close();
			}catch(Exception e){
				e.printStackTrace();
				out.println("SQL erro : " + e.getMessage());
			}
			
//		}
		 /*else{
			 System.out.println("조인이다!");
			disp = sevContext.getRequestDispatcher("/Join");
			disp.forward(req, resp);
		}*/
	}

}
