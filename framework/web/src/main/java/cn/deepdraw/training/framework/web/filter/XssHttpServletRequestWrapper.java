package cn.deepdraw.training.framework.web.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * XSS过滤处理
 * 
 * @author huangjiancheng 2018-12-10
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private final static HTMLFilter HTML_FILTER = new HTMLFilter(); // html过滤

	public XssHttpServletRequestWrapper(HttpServletRequest request) {

		super(request);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {

		if (!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(super.getHeader(HttpHeaders.CONTENT_TYPE))) { // 非json类型，直接返回

			return super.getInputStream();
		}
		String json = IOUtils.toString(super.getInputStream(), "utf-8");
		if (StringUtils.isBlank(json)) { // 为空，直接返回

			return super.getInputStream();
		}
		json = filter(json);
		final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes("utf-8"));
		return new ServletInputStream() { // xss过滤

			@Override
			public int read() throws IOException {

				return bis.read();
			}
		};
	}

	@Override
	public String getParameter(String name) {

		String value = super.getParameter(filter(name));
		if (StringUtils.isNotBlank(value)) {

			value = filter(value);
		}
		return value;
	}

	@Override
	public String[] getParameterValues(String name) {

		String[] parameters = super.getParameterValues(name);
		if (parameters == null || parameters.length == 0) {

			return null;
		}
		for (int i = 0; i < parameters.length; i++) {

			parameters[i] = filter(parameters[i]);
		}
		return parameters;
	}

	@Override
	public Map<String, String[]> getParameterMap() {

		Map<String, String[]> map = new LinkedHashMap<>();
		Map<String, String[]> parameters = super.getParameterMap();
		for (String key : parameters.keySet()) {

			String[] values = parameters.get(key);
			for (int i = 0; i < values.length; i++) {

				values[i] = filter(values[i]);
			}
			map.put(key, values);
		}
		return map;
	}

	@Override
	public String getHeader(String name) {

		String value = super.getHeader(filter(name));
		if (StringUtils.isNotBlank(value)) {

			value = filter(value);
		}
		return value;
	}

	private String filter(String input) {

		return HTML_FILTER.filter(input);
	}
}