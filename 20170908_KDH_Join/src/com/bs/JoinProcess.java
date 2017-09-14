package com.bs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class JoinProcess extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("utf-8");

		resp.setContentType("text/html;charset=utf-8");

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String duplicate = req.getParameter("duplicate");
		String idFromServer = "";
		boolean matchId = false;

		PrintWriter out = resp.getWriter();

		if (duplicate.equals("duplication")) { // 중복버튼 클릭했을때
			Connection conn = null;
			PreparedStatement pstmt = null;
			String queryS = "select * from login_info";
			try {
				Context context = new InitialContext();
				DataSource source = (DataSource) context
						.lookup("java:comp/env/jdbc/myconn");
				conn = source.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (id.equals("")) {

					out.println("<script type='text/javascript'>");
					out.println("alert('아이디를 입력해주세요.');");
					out.println("history.back();");
					out.println("</script>");
					out.flush();
				} else {

					pstmt = conn.prepareStatement(queryS);
					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						idFromServer = rs.getString("member_Id");

						System.out.println("서버 Id : " + idFromServer);
						System.out.println("입력ID : " + id);

						if (idFromServer.equals(id)) {
							matchId = true;
						}

					}
					if (matchId) {
						out.println("<script type='text/javascript'>");
						out.println("alert('중복된 아이디 존재!');");
						out.println("history.back();");
						out.println("</script>");
						matchId = false;
						out.flush();
					} else {
						out.println("<script type='text/javascript'>");
						out.println("alert('사용가능한 아이디!');");
						out.println("history.back();");
						out.println("</script>");
						out.flush();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (!duplicate.equals("duplication")) { // 전송버튼 클릭했을때
			out.println("<html><body><center>");

			if (id.equals("")) {

				out.println("<script type='text/javascript'>");
				out.println("alert('아이디를 입력해주세요.');");
				out.println("history.back();");
				out.println("</script>");
				out.flush();
			} else if (pw.equals("")) {

				out.println("<script type='text/javascript'>");
				out.println("alert('비밀번호를 입력해주세요.');");
				out.println("history.back();");
				out.println("</script>");
				out.flush();
			} else {
				Connection conn = null;
				PreparedStatement pstmt = null;
				String query = "insert into login_Info values (?, ?)";
				try {
					Context context = new InitialContext();
					DataSource source = (DataSource) context
							.lookup("java:comp/env/jdbc/myconn");
					conn = source.getConnection();

					pstmt = conn.prepareStatement(query);

					pstmt.setString(1, id);
					pstmt.setString(2, pw);

					int res = pstmt.executeUpdate();

					out.println("Save Success!");

					pstmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					out.println("SQL erro : " + e.getMessage());
				}

				out.println("</center></body></html>");
			}

		}

	}
}
