package com.automic.dataShare.taskServers.htkg.syndata.pcach;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.automic.dataShare.taskServers.htkg.syndata.SyndataBusi;

public class Pcach {

	private static Logger log = Logger.getLogger(Pcach.class);
	public static Hashtable<String,String> projectTB = new Hashtable<String,String>(0);
	private static int intv = 60 * 12;//分钟
	
	/**
	 * 启动自动任务
	 */
	public static void start(){
		log.info("===启动：实时监控系统测控设备信息维护任务！");
		SyndataBusi busi = new SyndataBusi();
		busi.projectHandler();
		log.info("===结束：实时监控系统测控设备信息维护任务！");
		new Thread(new PcachTask(busi)).start();
	}
	
	/**
	 * 获取设备gprsid
	 * @param projectId
	 * @return
	 */
	public static String getGprsId(String projectId){
		
		return projectTB.get(projectId);
	}
	/**
	 * 重置设备信息
	 * @param projectId
	 * @param gprsId
	 */
	public static void putGprsId(String projectId,String gprsId){
		
		projectTB.put(projectId, gprsId);
	}
	/**
	 * @author huaXinbo
	 * 设备缓存处理
	 */
	static class PcachTask implements Runnable{
		
		private SyndataBusi busi;
		
		/**
		 * 
		 */
		private PcachTask(SyndataBusi busi){
			this.busi = busi;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){				
				//任务等待
				try {
					Thread.sleep(intv * 60 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{}
				log.info("===启动：实时监控系统测控设备信息维护任务！");
				busi.projectHandler();
				log.info("===结束：实时监控系统测控设备信息维护任务！");
			}			
		}		
	}
}
