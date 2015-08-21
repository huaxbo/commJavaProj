package com.automic.httpSrv.srv;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;

import com.automic.httpSrv.impl.AmHttpRequest;
import com.automic.httpSrv.impl.AmHttpResponse;
import com.automic.httpSrv.inter.AmHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
/**
 * 
 * @author chuer
 * @Description: 服务器启动类
 * @date 2014年11月12日 下午3:53:38 
 * @version V1.0
 */
public class AmHttpServer {
	
	private static Logger log = Logger.getLogger(AmHttpServer.class);
	
    //启动服务，监听来自客户端的请求
	public static void start() throws IOException {
		log.info("启动AmHttpServer....");
		long st = System.currentTimeMillis();
		AmHttpContext.load();
		
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver = provider.createHttpServer(new InetSocketAddress(AmHttpContext.contextPort), 100);//监听端口8080,能同时接 受100个请求
		httpserver.createContext(AmHttpContext.contextPath, new AmHttpDispatcher()); 
		httpserver.setExecutor(null);
		httpserver.start();
		
		long et = System.currentTimeMillis();
		StringBuilder msg = new StringBuilder();		
		msg.append("服务端口：" + AmHttpContext.contextPort + ",");
		msg.append("应用服务上下文:" + AmHttpContext.contextPath);
		msg.append("\nAmHttpServer服务启动成功,耗时：" + (et-st) + "ms!");
		
		log.info(msg.toString());
	}
	
	/**
	 * @author Administrator
	 *
	 */
	static class AmHttpDispatcher implements HttpHandler {

		public void handle(HttpExchange httpExchange) throws IOException {
			AmHttpRequest request = new AmHttpRequest(httpExchange);
			AmHttpResponse response = new AmHttpResponse(httpExchange);
			AmHandler handler = AmHttpContext.getHandler(request.getReuestURI().getPath());
			handler.service(request, response);
		}
	}
}
