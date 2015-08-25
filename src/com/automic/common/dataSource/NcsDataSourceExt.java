package com.automic.common.dataSource;

public interface NcsDataSourceExt extends NcsDataSource {

	public abstract NcsConnectionExt getNcsConnetionExt(long paramLong);

	public abstract NcsConnectionExt getNcsConnetionExt();
}
