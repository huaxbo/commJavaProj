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
 * @Description: ������������
 * @date 2014��11��12�� ����3:53:38 
 * @version V1.0
 */
public class AmHttpServer {
	
	private static Logger log = Logger.getLogger(AmHttpServer.class);
	
    //�������񣬼������Կͻ��˵�����
	public static void start() throws IOException {
		log.info("����AmHttpServer....");
		long st = System.currentTimeMillis();
		AmHttpContext.load();
		
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver = provider.createHttpServer(new InetSocketAddress(AmHttpContext.contextPort), 100);//�����˿�8080,��ͬʱ�� ��100������
		httpserver.createContext(AmHttpContext.contextPath, new AmHttpDispatcher()); 
		httpserver.setExecutor(null);
		httpserver.start();
		
		long et = System.currentTimeMillis();
		StringBuilder msg = new StringBuilder();		
		msg.append("����˿ڣ�" + AmHttpContext.contextPort + ",");
		msg.append("Ӧ�÷���������:" + AmHttpContext.contextPath);
		msg.append("\nAmHttpServer���������ɹ�,��ʱ��" + (et-st) + "ms!");
		
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
