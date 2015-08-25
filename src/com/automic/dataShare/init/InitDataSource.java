/**
 * 
 */
package com.automic.dataShare.init;

import java.io.FileReader;
import java.util.ArrayList;

import com.automic.common.dataSource.DbConfigVO;
import com.automic.common.dataSource.NcsDataSourceFactoryExt;
import com.automic.dataShare.util.Constant;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;



/**
 * @author 滑新波
 *
 */
public class InitDataSource {

	private static XStream xstream = new XStream(new DomDriver());
	
	/**
	 * 初始化数据源
	 */
	public static void initDataSource(){
		try{
			xstream.alias("dataSources", ArrayList.class);
			xstream.alias("dataSource", DbConfigVO.class);
			@SuppressWarnings("unchecked")
			ArrayList<DbConfigVO> list = (ArrayList<DbConfigVO>) xstream.fromXML(new FileReader(InitDataSource.class.getResource("/config/dataSource.xml").getFile()));
			
			if(list != null && list.size()>0){
				DbConfigVO[] vos = new DbConfigVO[list.size()];
				int i=0;
				for(DbConfigVO vo:list){
					switch(i){
					case 0:Constant.DB_AUTOMIC = vo.dataSourceName;break;
					case 1:Constant.DB_HTKG = vo.dataSourceName;break;
					}
					vos[i++] = vo;
				}
				NcsDataSourceFactoryExt.init(vos);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
	}
	
}
