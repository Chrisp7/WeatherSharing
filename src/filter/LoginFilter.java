package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {"/realTime.do","/history.do","/uploadSuccess.html","/error.jsp","/success.html","/upload.html","/userpage.jsp","/logout.do", "/user2.do",
		"/allphoto.html","/error.jsp",  "/photoDetail.html" }, initParams = { @WebInitParam(name = "INDEX", value = "index.html") })
public class LoginFilter implements Filter {

	String INDEX=null;
	public LoginFilter() {
	}
	public void init(FilterConfig Config) throws ServletException {
		this.INDEX=Config.getInitParameter("INDEX");
	}
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession().getAttribute("login") != null) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(INDEX);
		}
	}


	

}
