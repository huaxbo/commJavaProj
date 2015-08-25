/**
 * 
 */
package com.automic.dataShare.taskServers.htkg;

import java.util.List;

import org.apache.log4j.Logger;

import com.automic.dataShare.taskServers.TaskServerInter;
import com.automic.dataShare.taskServers.htkg.exchange.ExchangeBusi;
import com.automic.dataShare.taskServers.htkg.syndata.SyndataBusi;
import com.automic.dataShare.taskServers.htkg.syndata.pcach.Pcach;
import com.automic.dataShare.taskServers.htkg.syndata.vos.SyndataVO;
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
		// 启动设备信息初始化任务
		Pcach.start();

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
		private SyndataBusi sbusi;
		private ExchangeBusi ebusi;

		/**
		 * 
		 */
		private DataHandler() {
			sbusi = new SyndataBusi();
			ebusi = new ExchangeBusi();
		}

		/**
		 * 计算轮训间隔
		 * 
		 * @return
		 */
		private void caculateIntv() {
			intv = 61 - Integer.parseInt(DateTime.mm());
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
				log.info("====开启：数据同步任务！");
				// 查询待同步记录
				List<SyndataVO> vos = sbusi.getSyndata();
				while (vos.size() > 0) {
					// 存储记录
					String[] ids = null;
					try{
						ids = ebusi.save(vos);						
					}catch(Exception e){
						log.error("转存实时数据异常：" + e);
						break;
					}finally{}
					// 删除同步完记录
					sbusi.delSyndata(ids);
					// 查询待同步记录
					vos = sbusi.getSyndata();
				}
				// 计算轮训间隔
				caculateIntv();
				log.info("====结束：数据同步任务，" + intv + "分后轮训执行数据同步任务！");
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
