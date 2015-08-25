package com.automic.dataShare.taskServers.htkg.syndata.pcach;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.automic.dataShare.taskServers.htkg.syndata.SyndataBusi;

public class Pcach {

	private static Logger log = Logger.getLogger(Pcach.class);
	public static Hashtable<String,String> projectTB = new Hashtable<String,String>(0);
	private static int intv = 60 * 12;//����
	
	/**
	 * �����Զ�����
	 */
	public static void start(){
		log.info("===������ʵʱ���ϵͳ����豸��Ϣά������");
		SyndataBusi busi = new SyndataBusi();
		busi.projectHandler();
		log.info("===������ʵʱ���ϵͳ����豸��Ϣά������");
		new Thread(new PcachTask(busi)).start();
	}
	
	/**
	 * ��ȡ�豸gprsid
	 * @param projectId
	 * @return
	 */
	public static String getGprsId(String projectId){
		
		return projectTB.get(projectId);
	}
	/**
	 * �����豸��Ϣ
	 * @param projectId
	 * @param gprsId
	 */
	public static void putGprsId(String projectId,String gprsId){
		
		projectTB.put(projectId, gprsId);
	}
	/**
	 * @author huaXinbo
	 * �豸���洦��
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
				//����ȴ�
				try {
					Thread.sleep(intv * 60 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{}
				log.info("===������ʵʱ���ϵͳ����豸��Ϣά������");
				busi.projectHandler();
				log.info("===������ʵʱ���ϵͳ����豸��Ϣά������");
			}			
		}		
	}
}
