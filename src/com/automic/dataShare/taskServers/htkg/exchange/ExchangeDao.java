package com.automic.dataShare.taskServers.htkg.exchange;

import java.sql.PreparedStatement;
import java.util.List;


import com.automic.dataShare.taskServers.htkg.syndata.pcach.Pcach;
import com.automic.dataShare.taskServers.htkg.syndata.vos.SyndataVO;
import com.automic.dataShare.util.CommonDao;
import com.automic.dataShare.util.Constant;

public class ExchangeDao extends CommonDao {

	/**
	 * 保存记录
	 * @param vos
	 * @return
	 */
	public String[] save(List<SyndataVO> vos) throws Exception {
		String[] ids = new String[vos.size()];
		StringBuilder sder = new StringBuilder();
		sder.append(" insert into wr_mp_exchange");
		sder.append("(gprsid,acc_w,tm)");
		sder.append(" values(?,?,?)");
		//初始连接
		getConAndStmt(Constant.DB_HTKG,sder.toString());
		if(preStmt != null){
			try{
				PreparedStatement pst = preStmt.getPreStmt();
				for(int i=0;i<vos.size();i++){
					SyndataVO vo = vos.get(i);
					ids[i] = vo.getId();
					int idx = 1;
					pst.setString(idx++, Pcach.getGprsId(vo.getProjectId()));					
					pst.setDouble(idx++, vo.getAccumuflux());
					pst.setString(idx++, vo.getTm());
					
					preStmt.addBatch();
				}				
				preStmt.executeBatch();
				con.commit();
				closeCon();//关闭数据库								
			}catch(Exception e){				
				con.rollback();
				closeCon();//关闭数据库
				
				throw e;
			}finally{}
		}
		
		return ids;
	}
}
