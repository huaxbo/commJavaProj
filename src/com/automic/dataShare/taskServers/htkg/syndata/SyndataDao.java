package com.automic.dataShare.taskServers.htkg.syndata;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.automic.dataShare.taskServers.htkg.syndata.pcach.Pcach;
import com.automic.dataShare.taskServers.htkg.syndata.vos.SyndataVO;
import com.automic.dataShare.util.CommonDao;
import com.automic.dataShare.util.Constant;

public class SyndataDao extends CommonDao {
	private Logger log = Logger.getLogger(SyndataDao.class);
	private int recs = 5000;
	
	/**
	 * @return
	 */
	public List<SyndataVO> getSyndata(){
		List<SyndataVO> vos = new ArrayList<SyndataVO>(recs);
		//��ʼ����
		getConAndStmt(Constant.DB_AUTOMIC);
		if(stmt != null){
			StringBuilder sder = new StringBuilder();
			sder.append(" select id,projectId,accumuflux,tm from syndata");
			sder.append(" where rownum < " + (recs+1));
			sder.append(" order by tm asc ");
			try {
				ResultSet rs = stmt.executeQuery(sder.toString());
				while(rs.next()){
					vos.add(new SyndataVO(rs.getString("id"),
							rs.getString("projectId"),
							rs.getDouble("accumuflux"), 
							rs.getString("tm")));
				}
				closeCon();//�ر����ݿ����
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e);
				closeCon();
			}finally{}
		}
		
		return vos;
	}
	/**
	 * ɾ����¼
	 * @param ids
	 */
	public void delSyndata(String[] ids){
		StringBuilder sder = new StringBuilder();
		sder.append("delete from syndata where id = ?");
		//��ʼ����
		getConAndStmt(Constant.DB_AUTOMIC,sder.toString());
		if(preStmt != null){
			try {
				int len = ids.length;
				PreparedStatement pst = preStmt.getPreStmt();
				for(int i=0;i<len;i++){
					pst.setString(1, ids[i]);
					preStmt.addBatch();
				}
				preStmt.executeBatch();
				con.commit();
				closeCon();//�ر����ݿ����
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e);
				con.rollback();
				closeCon();
			}finally{}
		}
	}
	/**
	 * �豸��Ϣ����
	 */
	public void projectHandler(){
		//��ʼ����
		getConAndStmt(Constant.DB_AUTOMIC);
		if(stmt != null){
			StringBuilder sder = new StringBuilder();
			sder.append("select id,gprsId,meterNo from project where gprsId is not null ");
			try {
				ResultSet rs = stmt.executeQuery(sder.toString());
				while(rs.next()){
					String id = rs.getString("id");
					String gprsId = rs.getString("gprsId");
					String meterNo = rs.getString("meterNo");
					if(id != null && gprsId != null){						
						Pcach.putGprsId(id, gprsId + meterNo);
					}
				}
				closeCon();//�ر����ݿ����
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e);
				closeCon();
			}finally{}
		}
	}
}
