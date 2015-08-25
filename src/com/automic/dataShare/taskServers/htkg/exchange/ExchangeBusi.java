package com.automic.dataShare.taskServers.htkg.exchange;

import java.util.List;

import com.automic.dataShare.taskServers.htkg.syndata.vos.SyndataVO;

public class ExchangeBusi {

	private ExchangeDao dao = new ExchangeDao();
	
	
	/**
	 * ±£´æ¼ÇÂ¼
	 * @param vos
	 * @return
	 */
	public String[] save(List<SyndataVO> vos) throws Exception{
		
		return dao.save(vos);
	}
	
	
}
