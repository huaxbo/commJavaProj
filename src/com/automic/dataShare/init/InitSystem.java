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
 * @author ���²�
 *
 */
public class InitSystem {

	private static Logger logger = Logger.getLogger(InitSystem.class);
		
	/**
	 * ��ʼ��������Ϣ
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
					logger.error("������[" + e.getName().trim() + "]�����ظ�����ǰ������Ч��");
				}else{
					SystemConfig.putConfig(e.getName().trim(), e.getText().trim());	
				}
			}
		}
	}
	
}
