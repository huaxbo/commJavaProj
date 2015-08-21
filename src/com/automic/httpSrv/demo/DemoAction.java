package com.automic.httpSrv.demo;


import com.automic.httpSrv.inter.AmHttpHandler;
import com.automic.httpSrv.inter.AmRequest;
import com.automic.httpSrv.inter.AmResponse;

public class DemoAction extends AmHttpHandler {

	/* (non-Javadoc)
	 * @see com.automic.httpSrv.inter.HttpHandler#doGet(com.automic.httpSrv.inter.Request, com.automic.httpSrv.inter.Response)
	 */
	@Override
	public void doGet(AmRequest request, AmResponse response) {
		// TODO Auto-generated method stub
		response.setEncoding(request.getEncoding());
		String g1 = request.getParamter("g1");
		String g2 = request.getParamter("g2");
		
		System.out.println(g1 + "::" + g2);		
		
		response.write("你就是你hello");
	}

	/* (non-Javadoc)
	 * @see com.automic.httpSrv.inter.HttpHandler#doPost(com.automic.httpSrv.inter.Request, com.automic.httpSrv.inter.Response)
	 */
	@Override
	public void doPost(AmRequest request, AmResponse response) {
		// TODO Auto-generated method stub		
		response.setEncoding(request.getEncoding());
		
		String p1 = request.getParamter("p1");
		String p2 = request.getParamter("p2");
		String p3 = request.getParamter("p3");
		String p4 = request.getParamter("p4");
		
		System.out.println(p1 + "::" + p2 + "::" + p3 + "::" + p4);
		
		response.write("我就是我world");
	}

}
