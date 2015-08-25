package com.automic.common.dataSource;

public interface NcsConnectionExt extends NcsConnection {

	 public abstract NcsStatementExt getNcsPreStatement(String sql);
}
