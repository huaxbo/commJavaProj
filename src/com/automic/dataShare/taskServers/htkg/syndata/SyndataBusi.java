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
	 * 删除记录
	 * @param ids
	 */
	public void delSyndata(String[] ids){
		
		dao.delSyndata(ids);
	}
	/**
	 * 设备信息处理
	 */
	public void projectHandler(){
		
		dao.projectHandler();
	}
	
}
