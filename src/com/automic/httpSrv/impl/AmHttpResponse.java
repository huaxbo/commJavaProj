package com.automic.httpSrv.impl;

import java.io.IOException;
import java.io.OutputStream;

import com.automic.httpSrv.inter.AmResponse;
import com.sun.net.httpserver.HttpExchange;

public class AmHttpResponse implements AmResponse {
	private HttpExchange httpExchange;
	private String encoding = "UTF-8";
	
	public AmHttpResponse(HttpExchange httpExchange) {
		this.httpExchange = httpExchange;
	}

	@Override
	public void write(String result) {
		try {			
			byte[] rlts = result.getBytes(encoding);
			httpExchange.sendResponseHeaders(200, rlts.length);// 设置响应头属性及响应信息的长度
			OutputStream out = httpExchange.getResponseBody(); // 获得输出流
			out.write(rlts);
			out.flush();
			httpExchange.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 设置编码
	 * @param encoding
	 */
	public void setEncoding(String encoding){
		this.encoding = encoding;
	}
}
