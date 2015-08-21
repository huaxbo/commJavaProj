package com.automic.httpSrv.inter;

public abstract class AmHttpHandler implements AmHandler {

	@Override
	public void service(AmRequest request, AmResponse response) {
		request.initRequestHeader();
		request.initRequestParam();
		if (request.getMethod().equals(AmRequest.GET)) {
			doGet(request, response);
		} else if (request.getMethod().equals(AmRequest.POST)) {
			request.initRequestBody();
			doPost(request, response);
		}
	}

	@Override
	public abstract void doGet(AmRequest request, AmResponse response);

	@Override
	public abstract void doPost(AmRequest request, AmResponse response);

}
