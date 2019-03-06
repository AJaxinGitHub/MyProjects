package com.pactera.common;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

public class HttpUtil {

	private static HttpUtil instance = new HttpUtil();
	private static HttpClient client;
	public static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

	private static ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			long keepAlive = super.getKeepAliveDuration(response, context);
			if (keepAlive == -1) {
				keepAlive = 5000;
			}
			return keepAlive;
		}
	};

	private HttpUtil() {
		client = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(keepAliveStrat).build();
	}

	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000)
			.setConnectionRequestTimeout(20000).build();

	/**
	 * Get service information content through the URL interface
	 * 
	 * @param url_prex the domain name of url
	 * @param url      the File name of url
	 * @param param    the Parameter section of url
	 * @return JSObject to String
	 * @throws HttpException
	 * @throws IOException
	 */
	public String requestHttpGet(String url_prex, String url, String param) throws HttpException, IOException {
		url = url_prex + url;
		if (param != null && !param.equals("")) {
			if (url.endsWith("?")) {
				url = url + param;
			} else {
				url = url + "?" + param;
			}
		}
		HttpRequestBase method = new HttpGet(url);
		method.setConfig(requestConfig);
		HttpResponse response = client.execute(method);
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			return "";
		}
		InputStream is = null;
		String responseData = "";
		try {
			is = entity.getContent();
			responseData = IOUtils.toString(is, "UTF-8");
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return responseData;
	}

	/**
	 * Instantiated object
	 * 
	 * @return HttpUtil
	 */
	public static HttpUtil getInstance() {
		return instance;
	}
}
