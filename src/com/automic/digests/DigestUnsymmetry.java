package com.automic.digests;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Hashtable;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.automic.util.CommonUtil;


/**
 * �ǶԳƼ���
 * @author huaxb
 *
 */
public class DigestUnsymmetry {
	//�����㷨
	public static enum meths{RSA}
	private final String password = "23*&^%";
	private KeyPair kp;
	private Cipher cp;

	private static Hashtable<String,Integer> keysize = new Hashtable<String,Integer>(0);	
	static{
		keysize.put(meths.RSA.toString(), 512);
	}
	/**
	 * @param meth �����㷨
	 */
	public DigestUnsymmetry(String meth ){
		try {			
			KeyPairGenerator kpg = KeyPairGenerator.getInstance(meth);
			kpg.initialize(512, new SecureRandom(password.getBytes()));
			kp = kpg.generateKeyPair();
			cp = Cipher.getInstance(meth);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ����
	 * @param src
	 * @return
	 */
	public byte[] encryptor(byte[] src){
		if(src == null){
			
			return null;
		}
		try {
			cp.init(Cipher.ENCRYPT_MODE, kp.getPublic());
			
			return cp.doFinal(src);
			
		} catch (InvalidKeyException | IllegalBlockSizeException 
				| BadPaddingException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * ����
	 * @param dst
	 * @return
	 */
	public byte[] decryptor(byte[] dst){
		if(dst == null){
			
			return null;
		}
		try {
			cp.init(Cipher.DECRYPT_MODE, kp.getPrivate());
			
			return cp.doFinal(dst);
			
		} catch (InvalidKeyException | IllegalBlockSizeException 
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String mess = "12345678";
		
		DigestUnsymmetry ds = new DigestUnsymmetry(meths.RSA.toString());
		System.out.println(meths.RSA);
		System.out.println("ԭ��:" + CommonUtil.byte2HexStr(mess.getBytes(), true));
		byte[] enbts = ds.encryptor(mess.getBytes());
		System.out.println("����:" + CommonUtil.byte2HexStr(enbts, true));
		System.out.println("����:" + CommonUtil.byte2HexStr(ds.decryptor(enbts), true));
		
		
	}

}
