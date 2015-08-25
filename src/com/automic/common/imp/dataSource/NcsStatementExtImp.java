package com.automic.common.imp.dataSource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.automic.common.dataSource.NcsStatementExt;

public class NcsStatementExtImp extends NcsStatementImp implements NcsStatementExt{

	private PreparedStatement preStmt;
	
	public NcsStatementExtImp(Statement statement,PreparedStatement preStmt) {
		super(statement);
		// TODO Auto-generated constructor stub
		this.preStmt = preStmt;
	}
		
	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		if(preStmt != null){
			try {
				preStmt.close();
				preStmt = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{}
		}
	}

	/* (non-Javadoc)
	 * @see com.automic.common.dataSource.NcsStatementExt#getPreStmt()
	 */
	@Override
	public PreparedStatement getPreStmt() {
		// TODO Auto-generated method stub
		return preStmt;
	}

	@Override
	public void addBatch() throws SQLException {
		// TODO Auto-generated method stub
		if(preStmt != null){
			preStmt.addBatch();
		}
	}

	/**
	 * 批量处理
	 * @throws SQLException 
	 */
	public void executeBatch() throws SQLException{
		if(preStmt != null){
			preStmt.executeBatch();
			preStmt.clearBatch();
		}
	}
	
}
