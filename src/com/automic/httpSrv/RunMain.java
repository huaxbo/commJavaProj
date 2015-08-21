package com.automic.httpSrv;

import java.io.IOException;

import com.automic.httpSrv.srv.AmHttpServer;

public class RunMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			AmHttpServer.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{}
	}

}
