package cn.deepdraw.training.framework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 跨域过滤器
 * 
 * @author huangjiancheng 2018-08-21
 */
public class CrossDomainFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (response instanceof HttpServletResponse) {

			addResponseHeaders(((HttpServletResponse) response));
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

	private void addResponseHeaders(HttpServletResponse response) {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE");
		response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Origin,X-Requested-With,Content-Type,Accept,key,secret");
		response.addHeader("Access-Control-Max-Age", "1728000");
	}
}