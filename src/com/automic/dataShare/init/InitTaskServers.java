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

import com.automic.dataShare.taskServers.TaskServerInter;

/**
 * @author 滑新波
 *
 */
public class InitTaskServers {

	private static Logger logger = Logger.getLogger(InitTaskServers.class);
	/**
	 * 初始化任务列表
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unchecked")
	public static void initTaskServers() throws JDOMException, IOException{
		
		SAXBuilder saxb = new SAXBuilder();
		Document doc = saxb.build(new File(InitTaskServers.class.getResource("/config/taskServers.xml").getFile()));
		Element root = doc.getRootElement();
		List<Element> children = root.getChildren();
		for(Element e :children){
			try {
				TaskServerInter ts = (TaskServerInter)Class.forName(e.getText().trim()).newInstance();
				if(ts != null){//启动任务
					ts.startTask();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.error("自动获取任务发生异常：\n");
				logger.error(e1);
			} finally{}
			
		}
		
	}
}
