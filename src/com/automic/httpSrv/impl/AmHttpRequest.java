package com.automic.httpSrv.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.automic.httpSrv.inter.AmRequest;
import com.sun.net.httpserver.HttpExchange;

public class AmHttpRequest implements AmRequest {
	private String encoding = "UTF-8";
	private HttpExchange httpExchange;
	private Map<String, String> paramMap = new HashMap<String, String>();
	private Map<String, List<String>> headMap = new HashMap<String, List<String>>();
	private String requestBody = "";

	public AmHttpRequest(HttpExchange httpExchange) {
		this.httpExchange = httpExchange;
	}

	@Override
	public String getParamter(String param) {
		return paramMap.get(param);
	}

	@Override
	public String getMethod() {
		return httpExchange.getRequestMethod().trim().toUpperCase();
	}

	@Override
	public URI getReuestURI() {
		return httpExchange.getRequestURI();
	}

	@Override
	public void initRequestParam() {
		String query = getReuestURI().getQuery();
		if(query == null){
			
			return ;
		}
		String[] arrayStr = query.split("&");
		for (String str : arrayStr) {
			paramMap.put(str.split("=")[0], str.split("=")[1]);
		}

	}

	@Override
	public void initRequestHeader() {
		for (String s : httpExchange.getRequestHeaders().keySet()) {
			headMap.put(s, httpExchange.getRequestHeaders().get(s));
		}
	}

	@Override
	public void initRequestBody() {
	
		InputStream in = httpExchange.getRequestBody(); // 获得输入流
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String temp = null;
		try {
			while ((temp = reader.readLine()) != null) {
				requestBody += temp;
			}			
			if(requestBody != null){//参数处理
				requestBody = URLDecoder.decode(requestBody,getEncoding());
				String[] arrayStr = requestBody.split("&");
				for (String str : arrayStr) {
					paramMap.put(str.split("=")[0], str.split("=")[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getRequestBody() {
		return requestBody;
	}

	/**
	 * 获取编码
	 * @return
	 */
	public String getEncoding(){
		List<String> lt = headMap.get("Content-type");
		if(lt != null && lt.size()>0){
			String ct = lt.get(0);
			if(ct.indexOf("charset=")>-1){
				try{
					String enc = new String(ct.substring(ct.indexOf("charset=") + "charset=".length()));
					
					return enc;
				}catch(Exception e){
					
					return encoding;
				}finally{} 				
			}
		}
		
		return encoding;
	}	
}
