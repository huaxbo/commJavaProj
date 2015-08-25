package com.automic.common.imp.dataSource;

import java.sql.Connection;

import com.automic.common.dataSource.NcsConnectionExt;
import com.automic.common.dataSource.NcsDataSourceExt;
import com.automic.common.imp.dataSource.pool.NcsDataSourceManager;

public class NcsDataSourceExtImp extends NcsDataSourceImp implements NcsDataSourceExt{
	private String connectPoolName;
	/**
	 * @param dataSourceName
	 */
	public NcsDataSourceExtImp(String dataSourceName) {
		super(dataSourceName);
		// TODO Auto-generated constructor stub
		connectPoolName = dataSourceName;
	}

	/* (non-Javadoc)
	 * @see com.automic.common.dataSource.NcsDataSourceExt#getNcsConnetionExt(long)
	 */
	@Override
	public NcsConnectionExt getNcsConnetionExt(long time) {
		// TODO Auto-generated method stub
		Connection c = 
		      NcsDataSourceManager.getInstance()
		      .getConnection(this.connectPoolName, time);
		    if (c == null) {
		      return null;
		    }
		    NcsConnectionExtImp cimp = new NcsConnectionExtImp(
		      this.connectPoolName, c);
		    
		    return cimp;
	}

	/* (non-Javadoc)
	 * @see com.automic.common.dataSource.NcsDataSourceExt#getNcsConnetionExt()
	 */
	@Override
	public NcsConnectionExt getNcsConnetionExt() {
		// TODO Auto-generated method stub
		Connection c = 
		      NcsDataSourceManager.getInstance()
		      .getConnection(this.connectPoolName);
		    if (c == null) {
		      return null;
		    }
		    NcsConnectionExtImp cimp = new NcsConnectionExtImp(
		      this.connectPoolName, c);
		    
		    return cimp;
	}

	
}
