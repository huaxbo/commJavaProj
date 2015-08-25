package com.automic.dataShare.util;

import org.apache.log4j.Logger;

import com.automic.common.dataSource.NcsConnectionExt;
import com.automic.common.dataSource.NcsDataSourceExt;
import com.automic.common.dataSource.NcsDataSourceFactoryExt;
import com.automic.common.dataSource.NcsStatementExt;
import com.automic.common.dataSource.NcsStatement;

public class CommonDao {
	
	Logger logger = Logger.getLogger(CommonDao.class);
	
	private long conTimeOut = 0;
	protected NcsDataSourceExt ds;
	protected NcsConnectionExt con;
	protected NcsStatement stmt;
	protected NcsStatementExt preStmt;
	
	
	/**
	 * 
	 */
	public CommonDao(){		
		conTimeOut = Long.parseLong(SystemConfig.getConfig("dataShare.conTimeOut"));
		if(conTimeOut < 0){
			conTimeOut = 0;
		}		
	}
	/**
	 * 获取数据库连接及语言对象
	 */
	protected void getConAndStmt(String dataSource,String ...sql){
		try{
			ds = NcsDataSourceFactoryExt.lookupDataSourceExt(dataSource);
			if(ds != null){
				if(conTimeOut>0){
					con = ds.getNcsConnetionExt(conTimeOut);
				}else{
					con = ds.getNcsConnetionExt();
				}
				if(con != null){
					stmt = con.getNcsStatement();	
					if(sql != null && sql.length>0){
						preStmt = con.getNcsPreStatement(sql[0]);
					}
				}
			}					
		}catch(Exception e){
			logger.error("获取数据库连接异常:");
			logger.error(e);
		}finally{}	
	}
	/**
	 * 释放数据库连接
	 */
	protected void closeCon(){
		if(stmt != null){
			stmt.close();
		}
		if(preStmt != null){
			preStmt.close();
		}
		if(con != null){
			con.free();
		}
		stmt = null;
		preStmt = null;
		ds = null;
	}
	
}
