package com.automic.common.imp.dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.automic.common.dataSource.NcsConnectionExt;
import com.automic.common.dataSource.NcsStatementExt;

public class NcsConnectionExtImp extends NcsConnectionImp implements NcsConnectionExt{
	Logger log = Logger.getLogger(NcsConnectionExtImp.class.getName());
	private Connection preConn;

	/**
	 * @param connectPoolName
	 * @param connection
	 */
	public NcsConnectionExtImp(String connectPoolName, Connection connection) {
		super(connectPoolName, connection);
		// TODO Auto-generated constructor stub
		preConn = connection;
	}

	/* (non-Javadoc)
	 * @see com.automic.common.dataSource.NcsConnectionExt#getNcsPreStatement(java.lang.String)
	 */
	public NcsStatementExt getNcsPreStatement(String sql)
	  {
	    try
	    {
	      Statement st = preConn.createStatement();
	      PreparedStatement preStmt = preConn.prepareStatement(sql);
	      NcsStatementExt ncs = new NcsStatementExtImp(st,preStmt);
	      return ncs;
	    } catch (Exception e) {
	     
	      log.error("创建数据库会话出错:\n", e);
	    }return null;
	  }
}
