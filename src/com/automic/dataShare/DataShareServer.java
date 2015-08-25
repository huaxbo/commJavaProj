/**
 * 
 */
package com.automic.dataShare;



import org.apache.log4j.Logger;

import com.automic.dataShare.init.InitDataSource;
import com.automic.dataShare.init.InitSystem;
import com.automic.dataShare.init.InitTaskServers;

/**
 * @author ���²�
 *
 */
public class DataShareServer {

	private static Logger logger = Logger.getLogger(DataShareServer.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//��ʼ��ϵͳ������Ϣ
		try {
			InitSystem.initSystem();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("��ʼϵͳ�������쳣��\n");
			logger.error(e);
		}finally{}
		//��ʼ�����ݿ�
		InitDataSource.initDataSource();
		//��ʼ���������
		try {
			InitTaskServers.initTaskServers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("������������쳣��\n");
			logger.error(e);
		} finally{}
		
	}
}
