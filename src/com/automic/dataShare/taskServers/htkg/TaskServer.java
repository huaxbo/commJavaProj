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
		// �����豸��Ϣ��ʼ������
		Pcach.start();

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
		 * ������ѵ���
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
				log.info("====����������ͬ������");
				// ��ѯ��ͬ����¼
				List<SyndataVO> vos = sbusi.getSyndata();
				while (vos.size() > 0) {
					// �洢��¼
					String[] ids = null;
					try{
						ids = ebusi.save(vos);						
					}catch(Exception e){
						log.error("ת��ʵʱ�����쳣��" + e);
						break;
					}finally{}
					// ɾ��ͬ�����¼
					sbusi.delSyndata(ids);
					// ��ѯ��ͬ����¼
					vos = sbusi.getSyndata();
				}
				// ������ѵ���
				caculateIntv();
				log.info("====����������ͬ������" + intv + "�ֺ���ѵִ������ͬ������");
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
