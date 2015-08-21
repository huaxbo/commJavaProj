package com.automic.httpCli;

import java.util.HashMap;

public class RunMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			//test get
			System.out.println(HttpCliCreater.requestGet("http://localhost:8080:/myApp/demoAction?g1=我们就是你&g2=hello好的"));
			
			//test post
			HashMap<String,String> params = new HashMap<String,String>(0);
			params.put("p1","hello456!我们");
			params.put("p2", "world123!他们");	
			
			String rlt = HttpCliCreater.requestPost("http://localhost:8080/myApp/demoAction",params);
			System.out.println(rlt);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{}
	}
	

}
