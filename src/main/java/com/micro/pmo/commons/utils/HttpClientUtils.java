package com.micro.pmo.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
	
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

	private static RequestConfig defaultRequestConfig = RequestConfig.custom()
			.setConnectTimeout(5000).setConnectionRequestTimeout(5000)
			.setSocketTimeout(30000).build();
	
	/**
	 * 
	 * 方法功能说明：POST数据
	 * 创建：2017年3月9日 by admin 
	 * 修改：日期 by 修改者
	 * 修改内容：
	 * @参数： @param url
	 * @参数： @param content
	 * @参数： @param contentType
	 * @参数： @return
	 * @参数： @throws Exception    
	 * @return String
	 *
	 */
	public static Result postString(String url, String content, String contentType, Map<String,String> headers) throws Exception {

		if (StringUtils.isEmpty(url)) {
			throw new NullPointerException("URL为空");
		}

		log.debug("[HTTP]发送数据: url={}, content={}", url, content);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost method = new HttpPost(url);

		if (MapUtils.isNotEmpty(headers)) {
			for (String key : headers.keySet()) {
				method.addHeader(key, headers.get(key));
			}
		}

		method.setConfig(defaultRequestConfig);

		method.addHeader("Content-Type", contentType);

		method.setEntity(new StringEntity(content, "utf-8"));
		
		String responseContent = null;

		try {
			// 执行请求
			HttpResponse response = httpclient.execute(method);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				// 成功
				responseContent = EntityUtils.toString(response.getEntity(), "utf-8");

				log.debug("[HTTP]接收数据: {}, {}", statusCode, responseContent);
				
				return Result.success(responseContent);

			} else {
				// 失败
				responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
				
				log.error("[HTTP]接收数据: {}, {}", statusCode, responseContent);
				
				return Result.failure(ResultState.HTTP_RESP_FAILED, null,responseContent);
			}
		} catch (Exception ex) {
			
			log.error("***发送消息时出现异常: " + url, ex);

			throw ex;
			
		} finally {
			if (method != null) {
				// 释放连接
				method.releaseConnection();
			}
			if (httpclient != null) {
				// 关闭实例
				httpclient.close();
			}
		}
	}
	
	/**
	 * 
	 * 方法功能说明：POST表单数据
	 * 
	 * 作者：Liu.Wen
	 * 时间: 2017年6月22日
	 * @return Result
	 *
	 */
	public static Result postFormData(String url, String contentType, Map<String,String> headers, Map<String, String> params) throws Exception {

		if (StringUtils.isEmpty(url)) {
			throw new NullPointerException("URL为空");
		}
		
		if (log.isDebugEnabled()) {
			log.debug("[HTTP]发送数据: url={}, content={}", url, (params != null ? params.toString() : null));
		}

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost method = new HttpPost(url);
		method.setConfig(defaultRequestConfig);
		method.addHeader("Content-Type", contentType);
		
		// 请求头
		if (MapUtils.isNotEmpty(headers)) {
			for (String key : headers.keySet()) {
				method.addHeader(key, headers.get(key));
			}
		}
		
		// 请求参数
		if (MapUtils.isNotEmpty(params)) {
			List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				paramList.add(new BasicNameValuePair(key, params.get(key)));
			}
			method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
		}
		
		String responseContent = null;

		try {
			// 执行请求
			HttpResponse response = httpclient.execute(method);

			int statusCode = response.getStatusLine().getStatusCode();
			
			if (statusCode == HttpStatus.SC_OK) {
				// 成功
				responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");

				log.debug("[HTTP]接收数据: {}, {}", statusCode, responseContent);
				
				return Result.success(responseContent);

			} else {
				// 失败
				responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
				
				log.error("[HTTP]接收数据: {}, {}", statusCode, responseContent);

				return Result.failure(ResultState.HTTP_RESP_FAILED, null,responseContent);
			}
		} catch (Exception ex) {
			
			log.error("***发送消息时出现异常: " + url, ex);

			throw ex;
			
		} finally {
			if (method != null) {
				// 释放连接
				method.releaseConnection();
			}
			if (httpclient != null) {
				// 关闭实例
				httpclient.close();
			}
		}
	}
	
	
	/**
	 * 
	 * 方法功能说明：PUT数据
	 * 创建：2017年3月9日 by admin 
	 * 修改：日期 by 修改者
	 * 修改内容：
	 * @参数： @param url
	 * @参数： @param content
	 * @参数： @param contentType
	 * @参数： @return
	 * @参数： @throws Exception    
	 * @return String
	 *
	 */
	public static Result putString(String url, String content, String contentType, Map<String,String> headers) throws Exception {

		if (StringUtils.isEmpty(url)) {
			throw new NullPointerException("URL为空");
		}

		log.debug("[HTTP]发送数据: url={}, content={}", url, content);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPut method = new HttpPut(url);

		if (MapUtils.isNotEmpty(headers)) {
			for (String key : headers.keySet()) {
				method.addHeader(key, headers.get(key));
			}
		}

		method.setConfig(defaultRequestConfig);

		method.addHeader("Content-Type", contentType);

		method.setEntity(new StringEntity(content, "utf-8"));
		
		String responseContent = null;

		try {
			// 执行请求
			HttpResponse response = httpclient.execute(method);

			int statusCode = response.getStatusLine().getStatusCode();
			
			if (statusCode == HttpStatus.SC_OK) {
				// 成功
				responseContent = EntityUtils.toString(response.getEntity(), "utf-8");

				log.debug("[HTTP]接收数据: {}, {}", statusCode, responseContent);
				
				return Result.success(responseContent);
				
			} else {
				// 失败
				responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
				
				log.error("[HTTP]接收数据: {}, {}", statusCode, responseContent);

				return Result.failure(ResultState.HTTP_RESP_FAILED, null,responseContent);
			}
		} catch (Exception ex) {
			
			log.error("***发送消息时出现异常: " + url, ex);

			throw ex;
			
		} finally {
			if (method != null) {
				// 释放连接
				method.releaseConnection();
			}
			if (httpclient != null) {
				// 关闭实例
				httpclient.close();
			}
		}
	}
	
	/**
	 * 
	 * 方法功能说明：以GET的方式获取数据
	 * 创建：2017年3月15日 by Liu.Wen 
	 * 修改：日期 by 修改者
	 * 修改内容：
	 * @参数： @param url
	 * @参数： @param content
	 * @参数： @param contentType
	 * @参数： @param headers
	 * @参数： @return    
	 * @return String
	 *
	 */
	public static final Result getString(String url, String contentType, Map<String,String> headers) throws Exception {
		
		if (StringUtils.isEmpty(url)) {
			throw new NullPointerException("URL为空");
		}

		log.debug("[HTTP]发送数据: url={}", url);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet method = new HttpGet(url);

		if (MapUtils.isNotEmpty(headers)) {
			for (String key : headers.keySet()) {
				method.addHeader(key, headers.get(key));
			}
		}

		method.setConfig(defaultRequestConfig);

		method.addHeader("Content-Type", contentType);

		String responseContent = null;

		try {
			// 执行请求
			HttpResponse response = httpclient.execute(method);

			int statusCode = response.getStatusLine().getStatusCode();
			
			if (statusCode == HttpStatus.SC_OK) {
				// 成功
				responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");

				log.debug("[HTTP]响应数据: {}, {}", statusCode, responseContent);
				
				return Result.success(responseContent);

			} else {
				// 失败
				responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");

				log.error("[HTTP]响应数据: {}, {}", statusCode, responseContent);

				return Result.failure(ResultState.HTTP_RESP_FAILED, null,responseContent);
			}
		} catch (Exception ex) {
			
			log.error("***HTTP请求调用出现异常: " + url, ex);

			throw ex;
			
		} finally {
			if (method != null) {
				// 释放连接
				method.releaseConnection();
			}
			if (httpclient != null) {
				// 关闭实例
				httpclient.close();
			}
		}
	}
	
	public static String readData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			StringBuilder ret;
			br = request.getReader();
			
			String line = br.readLine();
			if (line != null) {
				ret = new StringBuilder();
				ret.append(line);
			} else {
				return "";
			}
			
			while ((line = br.readLine()) != null) {
				ret.append('\n').append(line);
			}
			
			return ret.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
	}
	private static HttpURLConnection connection = null;
	
	public static String httpRequest(String url){
		String content = "";
		try{
		URL u = new URL(url);
		connection = (HttpURLConnection)u.openConnection();
		connection.setRequestMethod("GET");
		int code = connection.getResponseCode();
		if(code == 200){
		InputStream in = connection.getInputStream();
		InputStreamReader isr = new InputStreamReader(in,"utf-8");
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		while((line = reader.readLine()) != null){
		content += line;
		}
		}
		}catch(MalformedURLException e){
		e.printStackTrace();
		}catch(IOException e){
		e.printStackTrace();
		}finally{
		if(connection != null){
		connection.disconnect();
		}
		}
		return content;
		}

		public static void main(String[] args){
		String content = httpRequest("http://www.baidu.com/");
		System.out.println(content);
		}
}
