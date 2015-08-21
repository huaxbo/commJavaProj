package com.automic.httpSrv.inter;
/**
 * 
 * @Description: 相应类接口
 */
public interface AmResponse {
	
	public void write(String result);
	
	public void setEncoding(String encoding);
}
