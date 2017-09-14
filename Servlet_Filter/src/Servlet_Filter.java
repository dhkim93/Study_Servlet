import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class Servlet_Filter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest h_req = (HttpServletRequest)req;
		String method = h_req.getMethod();//요청 메서드 종류를 파악하기위함
		
		if(method.equalsIgnoreCase("POST")){//메서드가 post라면 utf-8로 지정하라
			req.setCharacterEncoding("utf-8");
			
		}
		
		chain.doFilter(req, resp);//다른 필터가 존재하면 그것도 처리하라
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
