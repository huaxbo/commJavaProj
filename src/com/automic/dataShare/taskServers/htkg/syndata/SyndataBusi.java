package com.automic.dataShare.taskServers.htkg.syndata;

import java.util.List;

import com.automic.dataShare.taskServers.htkg.syndata.vos.SyndataVO;

public class SyndataBusi {

	private SyndataDao dao = new SyndataDao();
	
	
	/**
	 * @return
	 */
	public List<SyndataVO> getSyndata(){
		
		return dao.getSyndata();
	}
	
	/**
	 * ɾ����¼
	 * @param ids
	 */
	public void delSyndata(String[] ids){
		
		dao.delSyndata(ids);
	}
	/**
	 * �豸��Ϣ����
	 */
	public void projectHandler(){
		
		dao.projectHandler();
	}
	
}
