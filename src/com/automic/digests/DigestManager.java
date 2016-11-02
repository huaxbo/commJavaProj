package com.automic.digests;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.automic.util.CommonUtil;

/**
 * 摘要加密
 * @author huaxb
 *
 */
public class DigestManager {

	public static final String MD5 = "MD5";
	public static final String SHA_1 = "SHA-1";
	public static final String SHA_256 = "SHA-256";
	
	
	/**
	 * 信息加密
	 * @param src
	 * @param algorithm ：MD5、SHA-1、SHA-256
	 * @return
	 */
	public static String digestMess(byte[] src,String algorithm,boolean space){
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(src);;
			
			return CommonUtil.byte2HexStr(md.digest(),space);	
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public static void main(String[] args){
		try {
			String src = "我";
			System.out.println(CommonUtil.byte2HexStr(src.getBytes("UTF-8"), true));
			System.out.println(digestMess(src.getBytes("UTF-8"),MD5,true));
			System.out.println(digestMess(src.getBytes("UTF-8"),SHA_1,true));
			System.out.println(digestMess(src.getBytes("UTF-8"),SHA_256,true));
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
