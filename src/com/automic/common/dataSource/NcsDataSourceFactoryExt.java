package com.automic.common.dataSource;

import com.automic.ACException;
import com.automic.common.imp.dataSource.NcsDataSourceExtImp;
import com.automic.common.imp.dataSource.pool.NcsDataSourceManager;

public class NcsDataSourceFactoryExt extends NcsDataSourceFactory {
	private static NcsDataSourceManager managerExt;

	/**
	 * @param vos
	 * @throws ACException
	 */
	public static void init(DbConfigVO[] vos) throws ACException {
		if (managerExt != null) {
			throw new ACException("数据源工厂不能重复初始化！");
		}
		if (vos == null) {
			throw new ACException("数据源初始化参数对象为空！");
		}
		managerExt = NcsDataSourceManager.getInstance(vos);
	}

	/**
	 * @param dataSourceName
	 * @return
	 * @throws ACException
	 */
	public static final NcsDataSourceExt lookupDataSourceExt(
			String dataSourceName) throws ACException {
		if (managerExt == null) {
			throw new ACException("查找数据源前首先必须初始化！");
		}
		return new NcsDataSourceExtImp(dataSourceName);
	}
}
