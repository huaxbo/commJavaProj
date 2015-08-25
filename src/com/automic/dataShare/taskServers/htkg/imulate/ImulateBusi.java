package com.automic.dataShare.taskServers.htkg.imulate;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.automic.dataShare.taskServers.htkg.syndata.pcach.Pcach;

public class ImulateBusi {

	private ImulateDao dao = new ImulateDao();
	
	private List<String> projIds;
	private static String[] pIds;
	
	/**
	 * 
	 */
	public ImulateBusi(){
		Hashtable<String,String> tb = Pcach.projectTB;
	 	Enumeration<String> es = tb.keys();
	 	pIds = new String[tb.size()];
	 	int idx = 0;
	 	while(es.hasMoreElements()){
	 		pIds[idx++] = es.nextElement();
	 	}
	}
	/**
	 * 
	 * @return
	 */
	public void initProjs(){
		
		projIds = dao.initProjs();
	}
	
	/**
	 * 
	 */
	public void imulate(){
		
		dao.update(pIds, projIds);		
	}
}
