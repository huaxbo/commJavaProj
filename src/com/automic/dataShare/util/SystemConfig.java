/**
 * 
 */
package com.automic.dataShare.util;

import java.util.Hashtable;

/**
 * @author ���²�
 *
 */
public class SystemConfig {
	
	private static Hashtable<String,String> systemTB = new Hashtable<String,String>(0);
	/**
	 * @param key
	 * @return
	 * ��ȡ������
	 */
	public static String getConfig(String key){
		if(key != null && !key.equals("")){
			return systemTB.get(key.trim());
		}
		
		return null;
	}
	/**
	 * �Ƿ����
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
	 * ����������
	 * @param key
	 * @param value
	 */
	public static void putConfig(String key,String value){
		systemTB.put(key, value);
	}
	
}

