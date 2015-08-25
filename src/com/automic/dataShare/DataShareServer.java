/**
 * 
 */
package com.automic.dataShare;



import org.apache.log4j.Logger;

import com.automic.dataShare.init.InitDataSource;
import com.automic.dataShare.init.InitSystem;
import com.automic.dataShare.init.InitTaskServers;

/**
 * @author 滑新波
 *
 */
public class DataShareServer {

	private static Logger logger = Logger.getLogger(DataShareServer.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//初始化系统配置信息
		try {
			InitSystem.initSystem();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("初始系统配置项异常：\n");
			logger.error(e);
		}finally{}
		//初始化数据库
		InitDataSource.initDataSource();
		//初始化任务服务
		try {
			InitTaskServers.initTaskServers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("启动任务服务异常：\n");
			logger.error(e);
		} finally{}
		
	}
}
