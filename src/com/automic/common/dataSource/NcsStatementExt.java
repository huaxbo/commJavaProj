package com.automic.common.dataSource;

import java.sql.PreparedStatement;

public interface NcsStatementExt extends NcsStatement {

	public void addBatch() throws Exception ;
	public void executeBatch() throws Exception;
	public PreparedStatement getPreStmt();
}
