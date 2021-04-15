package cn.deepdraw.training.framework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * XSS过滤
 * @author huangjiancheng
 * 2018-12-10
 */
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {

			request = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}