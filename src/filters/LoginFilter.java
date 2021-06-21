package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		System.out.println("------------LoginFilter------------in");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Object memberVO = session.getAttribute("memberVO");
//		System.out.println("LoginFilter:" + session.getId());
//		System.out.println("memberVO is null:" + (memberVO == null));

		if (memberVO == null) {
			session.setAttribute("location", req.getRequestURI());
//			System.out.println("location:" + session.getAttribute("location"));
			res.sendRedirect(req.getContextPath() + "/front-end/Login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
//		System.out.println("------------LoginFilter------------out");
	}

}
