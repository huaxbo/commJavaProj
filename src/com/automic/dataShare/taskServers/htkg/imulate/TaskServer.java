/**
 * 
 */
package com.automic.dataShare.taskServers.htkg.imulate;

import org.apache.log4j.Logger;

import com.automic.dataShare.taskServers.TaskServerInter;
import com.automic.util.DateTime;

/**
 * @author 滑新波
 * 
 */
public class TaskServer implements TaskServerInter {

	private Logger log = Logger.getLogger(TaskServer.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.automic.dataShare.taskServers.TaskServerInter#startTask() 启动任务
	 */
	@Override
	public void startTask() {
		// TODO Auto-generated method stub
		// 启动同步任务
		new Thread(new DataHandler()).start();
	}

	/**
	 * 数据处理任务
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
		 * 计算轮训间隔
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
				log.info("====开启：模拟数据任务！");
				busi.imulate();
				// 计算轮训间隔
				caculateIntv();
				log.info("====结束：模拟数据任务，" + intv + "分后轮训执行模拟数据任务！");
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
