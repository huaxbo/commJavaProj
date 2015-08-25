package com.automic.dataShare.taskServers.htkg.imulate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.automic.dataShare.util.CommonDao;
import com.automic.dataShare.util.Constant;
import com.automic.util.DateTime;

public class ImulateDao extends CommonDao {

	
	/**
	 * 
	 * @return
	 */
	public List<String> initProjs(){
		List<String> lt = new ArrayList<String>(0);
		getConAndStmt(Constant.DB_AUTOMIC);
		try{
			if(stmt != null){
				ResultSet rs = stmt.executeQuery("select projectId from realdata where type = '1' ");
				while(rs.next()){
					lt.add(rs.getString("projectId"));
				}
			}
			closeCon();			
		}catch(Exception e){
			e.printStackTrace();
			closeCon();	
		}finally{}
		
		return lt;
	}
	
	/**
	 * @param pIds
	 * @param up
	 */
	public void update(String[] pIds,List<String> ups){
		StringBuilder sder = new StringBuilder();		
		sder.append("update realdata ");
		sder.append(" set accumuflux = ?");
		sder.append(",dateTime = ?");						
		sder.append(" where projectId = ? and type = '1'");	
		getConAndStmt(Constant.DB_AUTOMIC,sder.toString());
		try{
			int i=0,cnt = 0;
			if(preStmt != null){
				int lth = pIds.length;
				String pId = null;
				String acc = geneAcc();
				String tm = DateTime.yyyy_MM_dd_HH_mm_ss();
				for(;i<lth;i++){
					pId = pIds[i];
					if(ups.contains(pId)){//更新						
						int idx = 1;
						PreparedStatement pst = preStmt.getPreStmt();
						pst.setString(idx++, acc);
						pst.setString(idx++, tm);
						pst.setString(idx++, pId);
						preStmt.addBatch();		
						cnt++;
					}
				}
				if(cnt>0){
					preStmt.executeBatch();
					con.commit();	
				}
			}
			closeCon();	
			//插入
			if(cnt == 0 || cnt < i){
				save(pIds,ups);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			con.rollback();
			closeCon();	
		}finally{}
	}
	/**
	 * @param pIds
	 * @param up
	 */
	public void save(String[] pIds,List<String> ups){
		StringBuilder sder = new StringBuilder();		
		sder.append(" insert into realdata(id,projectId,accumuflux,dateTime,type)");
		sder.append(" values(?,?,?,?,'1')");
		
		getConAndStmt(Constant.DB_AUTOMIC,sder.toString());
		try{
			if(preStmt != null){
				int lth = pIds.length;
				String pId = null;
				String acc = geneAcc();
				String tm = DateTime.yyyy_MM_dd_HH_mm_ss();
				int cnt = 0;
				for(int i=0;i<lth;i++){
					pId = pIds[i];
					if(!ups.contains(pId)){//插入						
						int idx = 1;
						PreparedStatement pst = preStmt.getPreStmt();
						pst.setString(idx++, IDGenerator.generate());
						pst.setString(idx++, pId);
						pst.setString(idx++, acc);
						pst.setString(idx++, tm);
						preStmt.addBatch();		
						cnt++;
					}
				}
				preStmt.executeBatch();
				con.commit();
			}
			closeCon();			
		}catch(Exception e){
			e.printStackTrace();
			con.rollback();
			closeCon();	
		}finally{}
	}
	/**
	 * @return
	 */
	private String geneAcc(){
		StringBuilder sder = new StringBuilder();
		sder.append(DateTime.dd());
		sder.append(DateTime.HH());
		sder.append(DateTime.mm());		
		sder.append("." + DateTime.ss());
		
		return sder.toString();
	}
}
