package edu.iba.controlModel;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import edu.iba.dataModel.User;
/**
 * Servlet Filter implementation class isLoggedIn
 */
public class isLoggedIn implements Filter {

    /**
     * Default constructor. 
     */
    public isLoggedIn() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpSession session = ((HttpServletRequest)request).getSession();
		// pass the request along the filter chain
		if(session.getAttribute("User") == null || ((User)session.getAttribute("User")).getRole()==null){
			((HttpServletRequest)request).setAttribute("Message", "Вы не авторизованы.");
			((HttpServletRequest)request).getRequestDispatcher("/login").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
