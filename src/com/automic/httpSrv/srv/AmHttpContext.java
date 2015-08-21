package com.automic.httpSrv.srv;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.automic.httpSrv.inter.AmHttpHandler;
import com.automic.httpSrv.util.XmlUtils;

/**
 * 
 * @author chuer
 * @Description: 上下文 
 * @date 2014年11月12日 下午3:53:48 
 * @version V1.0
 */
public class AmHttpContext {
	private static Map<String,AmHttpHandler> contextMap = new HashMap<String,AmHttpHandler>();
	public static String contextPath = "";
	public static int contextPort = 8080;
	
	/**
	 * 
	 */
	public static void load(){
		try{
			Document doc = XmlUtils.load(AmHttpContext.class.getResource("/").getPath()+"am-context.xml");
			Element root = doc.getDocumentElement();
			
			contextPath = XmlUtils.getAttribute(root,"context");
			contextPort = Integer.parseInt(XmlUtils.getAttribute(root,"port"));
			
			Element[] handlers = XmlUtils.getChildrenByName(root, "handler");
			for(Element ele : handlers){
				String handle_class = XmlUtils.getChildText(ele, "handler-class");
				String url_pattern = XmlUtils.getChildText(ele, "url-pattern");
				
				Class<?> cls = Class.forName(handle_class);
				Object newInstance = cls.newInstance();
				if(newInstance instanceof AmHttpHandler){
					contextMap.put(contextPath + url_pattern, (AmHttpHandler)newInstance);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static AmHttpHandler getHandler(String key){
		return contextMap.get(key);
	}
	
}
