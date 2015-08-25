/**
 * 
 */
package com.automic.dataShare.util;

import java.util.Hashtable;

/**
 * @author 滑新波
 *
 */
public class SystemConfig {
	
	private static Hashtable<String,String> systemTB = new Hashtable<String,String>(0);
	/**
	 * @param key
	 * @return
	 * 获取配置项
	 */
	public static String getConfig(String key){
		if(key != null && !key.equals("")){
			return systemTB.get(key.trim());
		}
		
		return null;
	}
	/**
	 * 是否存在
	 * @param key
	 * @return
	 */
	public static boolean exist(String key){
		if(key != null && !key.equals("")){
			return systemTB.containsKey(key.trim());
		}
		return false;
	}
	
	/**
	 * 放置配置项
	 * @param key
	 * @param value
	 */
	public static void putConfig(String key,String value){
		systemTB.put(key, value);
	}
	
}

