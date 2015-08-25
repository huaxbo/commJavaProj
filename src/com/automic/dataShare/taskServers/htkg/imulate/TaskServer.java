/**
 * 
 */
package com.automic.dataShare.taskServers.htkg.imulate;

import org.apache.log4j.Logger;

import com.automic.dataShare.taskServers.TaskServerInter;
import com.automic.util.DateTime;

/**
 * @author ���²�
 * 
 */
public class TaskServer implements TaskServerInter {

	private Logger log = Logger.getLogger(TaskServer.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.automic.dataShare.taskServers.TaskServerInter#startTask() ��������
	 */
	@Override
	public void startTask() {
		// TODO Auto-generated method stub
		// ����ͬ������
		new Thread(new DataHandler()).start();
	}

	/**
	 * ���ݴ�������
	 * 
	 * @author huaXinbo
	 * 
	 */
	class DataHandler implements Runnable {
		private int intv = 0;
		private ImulateBusi busi;

		/**
		 * 
		 */
		private DataHandler() {
			busi = new ImulateBusi();
			busi.initProjs();
		}

		/**
		 * ������ѵ���
		 * 
		 * @return
		 */
		private void caculateIntv() {
			intv = 60 - Integer.parseInt(DateTime.mm());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while (true) {
				// TODO Auto-generated method stub
				log.info("====������ģ����������");
				busi.imulate();
				// ������ѵ���
				caculateIntv();
				log.info("====������ģ����������" + intv + "�ֺ���ѵִ��ģ����������");
				try {
					Thread.sleep(intv * 60 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
				}
			}
		}
	}

}
