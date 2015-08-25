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
			throw new ACException("����Դ���������ظ���ʼ����");
		}
		if (vos == null) {
			throw new ACException("����Դ��ʼ����������Ϊ�գ�");
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
			throw new ACException("��������Դǰ���ȱ����ʼ����");
		}
		return new NcsDataSourceExtImp(dataSourceName);
	}
}
