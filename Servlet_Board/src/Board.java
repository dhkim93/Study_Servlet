import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Board extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");

		/*String subject = "";
		String author = "";
		String pw = "";
		String contents = "";
		String filename1 = "";
		String filename = "";*/
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		out.println("<html><body><center>");
		out.println("<form method=post action=Success enctype=multipart/form-data>");
		out.println("<table border=1 >");
		out.println("<tr>");
		out.println("<th widht=80>제목 :</th>");
		out.println("<td colspan=3><input type=text name=subject size=20 /></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th width=80>작성자 :</th>");
		out.println("<td><input type=text name=author size=20 /></td>");
		out.println("<th widht=80>비밀번호 :</th>");
		out.println("<td><input type=password name=pw size=20 /></td>");
		out.println("</tr>");
		out.println("<tr height=150>");
		out.println("<th widht=80>내용 :</th>");
		out.println("<td colspan=3 align=right><textarea name=contents cols=70 rows=10></textarea></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th widht=80>첨부파일 :</th>");
		out.println("<td colspan=3 ><input type=file name=filename1 value=찾아보기 /></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th width=80></th>");
		out.println("<td colspan=3><input type=submit value=전송 />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=reset value=취소 /></td>");
		out.println("</tr>");
		out.println("</table></form>");
		out.println("");

		/*Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into board values (null, ?, ?, ?, ?, ?)";

		boolean check = ServletFileUpload.isMultipartContent(req);
		System.out.println("Check 값: "+ check);
		try {
			Context serverCon = new InitialContext();
			DataSource source = (DataSource) serverCon
					.lookup("java:comp/env/jdbc/myconn");
			conn = source.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (check) {
			try {
				ServletContext context = this.getServletContext();
				String path = context.getRealPath("/upload");
				File dir = new File(path);
				
				if (!dir.exists())
					dir.mkdir();
				
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(10 * 1024);
				factory.setRepository(dir);
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(10 * 1024 * 1024);
				upload.setHeaderEncoding("utf-8");
				
				ArrayList items = (ArrayList) upload.parseRequest(req);
				
				for(int i=0; i < items.size(); i++){
					FileItem item = (FileItem) items.get(i);
					String value = item.getString("utf-8");
					
					if (item.isFormField()) {//form데이터라면 실행
						if (item.getFieldName().equals("subject"))
							subject  = value;
						else if (item.getFieldName().equals("author"))
							author  = value;
						else if (item.getFieldName().equals("pw"))
							pw = value;
						else if (item.getFieldName().equals("contents"))
							contents  = value;
					} else{
						if(item.getFieldName().equals("filename1")){
							filename1=item.getName();
							filename=filename1;
							System.out.println("filename1@@@@@ "+ filename1);
							System.out.println("filename@@@@@ "+ filename);
							
							if (filename1 == null
									|| filename1.trim().length() == 0)//파일 name이 공백이거나 길이가 0일경우(첨부파일 지정하지 않았을경우)
								continue;
							
							filename1 = filename1.substring(filename1.lastIndexOf("\\") +1);
							System.out.println("filename1 "+ filename1);
							File file = new File(dir, filename1);
							item.write(file);
							System.out.println("filename "+ filename);
							
						}
					}
				}	
				
				System.out.println("subject " + subject);
				System.out.println("author "+ author);
				System.out.println("pw  "+ pw);
				System.out.println("contents  "+ contents);
				
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, subject);
				pstmt.setString(2, author);
				pstmt.setString(3, pw);
				pstmt.setString(4, contents);
				pstmt.setString(5, filename);
//				pstmt.setString(5, dir);

				int res = pstmt.executeUpdate();


				out.println("제목 : " + subject + "<br/>");
				out.println("작성자 : " + author + "<br/>");
				out.println("비번 : " + pw + "<br/>");
				out.println("내용 : " + contents + "<br/>");
				out.println("사진 저장 경로 : " + dir + "<br/>");
				out.println("파일 이름 : " + filename + "<br/>");
				
				pstmt.close();
				conn.close();

			}catch (Exception e) {
				e.printStackTrace();
			} */
			/*catch (SizeLimitExceededException e) {
				e.printStackTrace();
			} catch (FileUploadException e) {
				e.printStackTrace();
			} 

		}*/
		out.println("</center></body></html>");
	}

}
