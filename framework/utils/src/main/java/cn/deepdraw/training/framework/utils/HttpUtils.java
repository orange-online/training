package cn.deepdraw.training.framework.utils;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response.Status;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.deepdraw.training.framework.utils.collection.MultiValueHashMap;
import cn.deepdraw.training.framework.utils.collection.MultiValueMap;

/**
 * Http工具类
 * @author huangjiancheng
 * 2018-12-05
 */
public final class HttpUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	private HttpUtils() {
	}

	/**
	 * 得到客户端的ip
	 */
	public static String getIp(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (org.apache.commons.lang3.StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getHeader("Proxy-Client-IP");
		}
		if (org.apache.commons.lang3.StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (org.apache.commons.lang3.StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 得到本地ip
	 */
	public static Set<String> getLocalIps() {

		Set<String> ips = new HashSet<String>();
		Enumeration<NetworkInterface> allNetInterfaces;
		try {

			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException ignored) {

			logger.error("getNetworkInterfaces failed, ", ignored);
			return ips;
		}
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {

			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
			Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {

				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {

					ips.add(ip.getHostAddress());
				}
			}
		}
		return ips;
	}

	public static Reply get(String uri, MultiValueMap<String, String> parameters) {

		return doGet(uri, parameters, new MultiValueHashMap<String, String>());
	}

	public static Reply post(String uri, MultiValueMap<String, String> parameters) {

		return doPost(uri, parameters, new MultiValueHashMap<String, String>());
	}

	public static Reply get(String uri, MultiValueMap<String, String> parameters, MultiValueMap<String, String> headers) {

		return doGet(uri, parameters, headers);
	}

	public static Reply post(String uri, MultiValueMap<String, String> parameters, MultiValueMap<String, String> headers) {

		return doPost(uri, parameters, headers);
	}

	private static CloseableHttpClient getHttpClient() {

		try {

			SSLContext sslContext = SSLContext.getInstance("TLS");
			X509TrustManager xtm = getX509TrustManager();
			sslContext.init(null, new TrustManager[] { xtm }, null);
			ConnectionSocketFactory csf = PlainConnectionSocketFactory.getSocketFactory();
			LayeredConnectionSocketFactory scsf = SSLConnectionSocketFactory.getSocketFactory();
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", csf).register("https", scsf).build();
			PoolingHttpClientConnectionManager mgr = new PoolingHttpClientConnectionManager(registry);
			return HttpClients.custom().setConnectionManager(mgr).build();
		} catch (Exception e) {

			logger.error("Unknown exception, ", e);
			return null;
		}
	}

	private static X509TrustManager getX509TrustManager() {

		return new X509TrustManager() {

			public X509Certificate[] getAcceptedIssuers() {

				return null;
			}

			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}
		};
	}

	private static Reply doGet(String uri, MultiValueMap<String, String> parameters, MultiValueMap<String, String> headers) {

		try {

			CloseableHttpClient client = getHttpClient();
			List<NameValuePair> params = getNameValuePairs(parameters);
			HttpGet method = getHttpGet(uri, params, headers);
			HttpResponse response = client.execute(method);
			return formatResponse(method, client, response);
		} catch (IOException e) {

			logger.error("Call failed, url is " + uri + ", parameters is " + JsonUtils.toJson(parameters), e);
			return new Reply(Status.SERVICE_UNAVAILABLE, "Service Unavailable.");
		}
	}

	private static void releaseConnection(HttpRequestBase method) {

		if (method != null) {

			method.releaseConnection();
		}
	}

	private static void catchHttpClientCloseException(HttpRequestBase method, CloseableHttpClient client) {

		try {

			releaseConnection(method);
			if (client != null) {

				client.close();
			}
		} catch (IOException e) {

			logger.error("close HttpClient failed, ", e);
		}
	}

	private static Reply formatResponse(HttpRequestBase httpRequest, CloseableHttpClient client, HttpResponse response) {

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {

			StringBuilder errorLog = new StringBuilder(httpRequest.getMethod() + " Call failed, URI is " + httpRequest.getURI());
			if (httpRequest instanceof HttpPost) {

				errorLog.append(", entity is " + ((HttpPost) httpRequest).getEntity());
			}
			errorLog.append(", status line is " + response.getStatusLine());
			logger.error(errorLog.toString());
		}
		try {

			return new Reply(Status.fromStatusCode(statusCode), EntityUtils.toString(response.getEntity(), Consts.UTF_8));
		} catch (ParseException | IOException e) {

			logger.error("Method EntityUtils.toString execute failed, ", e);
			return new Reply(Status.BAD_REQUEST, "");
		} finally {

			catchHttpClientCloseException(httpRequest, client);
		}
	}

	private static HttpGet getHttpGet(String uri, List<NameValuePair> parameters, MultiValueMap<String, String> headers) throws ParseException, IOException {

		String paramsValue = EntityUtils.toString(new UrlEncodedFormEntity(parameters, Consts.UTF_8));
		HttpGet httpGet = new HttpGet(uri + "?" + paramsValue);
		for (String key : headers.keySet()) {

			for (String value : headers.get(key)) {

				httpGet.addHeader(key, value);
			}
		}
		return httpGet;
	}

	private static Reply doPost(String uri, MultiValueMap<String, String> parameters, MultiValueMap<String, String> headers) {

		try {

			CloseableHttpClient client = getHttpClient();
			List<NameValuePair> params = getNameValuePairs(parameters);
			HttpPost method = getHttpPost(uri, params, headers);
			HttpResponse response = client.execute(method);
			return formatResponse(method, client, response);
		} catch (IOException e) {

			logger.error("Call failed, url is " + uri + ", parameters is " + JsonUtils.toJson(parameters), e);
			return new Reply(Status.SERVICE_UNAVAILABLE, "Service Unavailable.");
		}
	}

	private static HttpPost getHttpPost(String uri, List<NameValuePair> parameters, MultiValueMap<String, String> headers) {

		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(new UrlEncodedFormEntity(parameters, Consts.UTF_8));
		for (String key : headers.keySet()) {

			for (String value : headers.get(key)) {

				httpPost.addHeader(key, value);
			}
		}
		return httpPost;
	}

	private static List<NameValuePair> getNameValuePairs(MultiValueMap<String, String> parameters) {

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : parameters.keySet()) {

			for (String value : parameters.get(key)) {

				nameValuePairs.add(new BasicNameValuePair(key, value));
			}
		}
		return nameValuePairs;
	}

	public static class Reply {

		private Status status;

		private String content;

		public Reply(Status status, String content) {

			this.status = status;
			this.content = content;
		}

		public Status getStatus() {
			return status;
		}

		public String getContent() {
			return content;
		}
	}
}