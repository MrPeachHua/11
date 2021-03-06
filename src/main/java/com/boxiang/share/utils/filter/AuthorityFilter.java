package com.boxiang.share.utils.filter;

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

import org.apache.log4j.Logger;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.user.po.UserInfo;

public class AuthorityFilter implements Filter {
	private static final Logger logger = Logger.getLogger(AuthorityFilter.class);
	protected String		encoding		= null;

	protected String		forwardPath		= null;

	protected FilterConfig	filterConfig	= null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		this.forwardPath = filterConfig.getInitParameter("forwardpath");
	}

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	protected String selectEncoding(ServletRequest request) {
		return this.encoding;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//检查编码字符集
		if (request.getCharacterEncoding() == null) {
			String encoding = selectEncoding(request);
			if (encoding != null) request.setCharacterEncoding(encoding);
		}
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String requesturi = httpServletRequest.getRequestURI();
		logger.info("this is request uri:"+requesturi);
		if(requesturi.startsWith("/share/app/") || requesturi.startsWith("/share/payment/") || requesturi.startsWith("/share/other/")){
			chain.doFilter(request, response);
			return;
		}
		HttpSession session = httpServletRequest.getSession();
		UserInfo currentUser = (UserInfo) session.getAttribute(Constants.LOGIN_USER);

		if (currentUser == null &&
				!requesturi.endsWith("/main.html") && 
				!requesturi.endsWith("/logout.html") && 
				!requesturi.endsWith("/index.jsp") && 
				!requesturi.endsWith("/login.jsp") && 
				!requesturi.endsWith(httpServletRequest.getContextPath() + "/")) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.jsp");
			return;
		}
		chain.doFilter(request, response);
	}
}
