package com.automic.httpSrv.inter;

import java.net.URI;
/**
 * @Description: ÇëÇó½Ó¿Ú
 */
public interface AmRequest {
	public final static String GET = "GET";
	public final static String POST = "POST";

	public String getParamter(String param);

	public String getMethod();

	public URI getReuestURI();

	public void initRequestHeader();
	
	public void initRequestParam();

	public void initRequestBody();

	public String getRequestBody();
	
	public String getEncoding();
}
