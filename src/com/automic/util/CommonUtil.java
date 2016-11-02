package com.automic.util;

public class CommonUtil {

	/**
	 * byte数组转hex字符串
	 * @param bts
	 * @param space：是否保留空格
	 * @return
	 */
	public static String byte2HexStr(byte[] bts,boolean space){
		StringBuilder sder = new StringBuilder();
		for(int i=0;i<bts.length;i++){
			if(((int)bts[i] & 0xFF) < 16){
				sder.append("0");
			}
			sder.append(Integer.toHexString((int)bts[i] & 0xFF));
			if(space){
				if(i < bts.length-1){
					sder.append(" ");					
				}
			}
		}
		if(sder.length() > 0){
			
			return sder.toString();
		}
		
		return sder.toString();
	}
	
	/**
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexStr2byte(String hex){
		if(hex == null || hex.equals("")){
			
			return null;
		}
		hex = hex.replaceAll(" ","");
		byte[] bts = new byte[hex.length()/2];
		int k=0;
		for(int i=0;i<hex.length();i+=2){
			bts[k++] = Integer.valueOf(hex.substring(i,i+2), 16).byteValue();	
		}
		
		return bts;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		byte[] bts = hexStr2byte("b2");
		
		System.out.println(byte2HexStr(bts,true));
	}
}
