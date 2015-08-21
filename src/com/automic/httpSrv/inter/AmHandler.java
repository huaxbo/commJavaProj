package com.automic.httpSrv.inter;
/**
 * @Description: 消息处理接口
 */
public interface AmHandler {
	public void service(AmRequest request, AmResponse response);

	public void doGet(AmRequest request, AmResponse response);

	public void doPost(AmRequest request, AmResponse response);

}
