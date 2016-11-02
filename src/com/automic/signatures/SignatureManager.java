package com.automic.signatures;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

import com.automic.util.CommonUtil;

public class SignatureManager {

	private final String password = "$%^&*(wert";
	private KeyPair kp;
	private Signature sg;
	
	/**
	 * 
	 */
	public SignatureManager(){
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024,new SecureRandom(password.getBytes()));
			kp = kpg.generateKeyPair();
			sg = Signature.getInstance("SHA1WithRSA");
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 签名
	 * @param mess
	 * @return
	 */
	public String signatureMess(String mess){
		if(mess == null){
			
			return null;
		}
		try {
			sg.initSign(kp.getPrivate());
			sg.update(mess.getBytes());
			
			return CommonUtil.byte2HexStr(sg.sign(),false);
			
		} catch (InvalidKeyException | SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 校验签名
	 * @param mess
	 * @param sign
	 * @return
	 */
	public boolean verifyMess(String mess,String sign){
		if(mess == null || sign == null){
			
			return false;
		}
		try {
			sg.initVerify(kp.getPublic());
			sg.update(mess.getBytes());
			
			return sg.verify(CommonUtil.hexStr2byte(sign));
			
		} catch (InvalidKeyException | SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
			
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SignatureManager smg = new SignatureManager();
		String mess = "helloslllllefefe";
		String sgm = smg.signatureMess(mess);
		System.out.println("签名：" + sgm);
		//sgm = "36b2be69bbea98c65b87b89117de98bb7a5c90095380688a3ac66a4ebc544360c99dd3c5f9fa4b1adba1c899b0af6c946900362aca1c814b384200a61045f5670b0a188e12218903900218cf1ffc11e20a4d626a7aac36b01d64d135c850fb4f19668264250834aa904810fe1b62b86052c0f356c8aa5b653b1f8486aa9bc962";
//		mess = "helloslllllefef2";
		System.out.println(smg.verifyMess(mess, sgm));
	}

}
