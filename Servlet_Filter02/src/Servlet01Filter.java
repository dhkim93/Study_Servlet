import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Servlet01Filter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String id= req.getParameter("ID");
		
		HttpServletRequest h_req = (HttpServletRequest)req;
		HttpSession session = h_req.getSession();
		Object h_id = session.getAttribute("ID");
		
		if(id ==null|| id.trim().length()==0){
			HttpServletResponse h_resp = (HttpServletResponse)resp;
			h_resp.sendRedirect("login_err.html");
		}
		chain.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
