/**
 * 
 */
package com.automic.dataShare.init;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.automic.dataShare.util.SystemConfig;

/**
 * @author 滑新波
 *
 */
public class InitSystem {

	private static Logger logger = Logger.getLogger(InitSystem.class);
		
	/**
	 * 初始化配置信息
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	@SuppressWarnings("unchecked")
	public static void initSystem() throws JDOMException, IOException{
		SAXBuilder saxb = new SAXBuilder();
		Document doc = saxb.build(new File(InitTaskServers.class.getResource("/config/system.xml").getFile()));
		Element root = doc.getRootElement();
		List<Element> children = root.getChildren();
		for(Element e:children){
			if(e != null && e.getText() != null){
				if(SystemConfig.exist(e.getName().trim())){
					logger.error("配置项[" + e.getName().trim() + "]配置重复，当前配置无效！");
				}else{
					SystemConfig.putConfig(e.getName().trim(), e.getText().trim());	
				}
			}
		}
	}
	
}
