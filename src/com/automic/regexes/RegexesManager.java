package com.automic.regexes;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������ʽУ��
 * @author huaxb
 *
 */
public class RegexesManager {

	/**
	 * @param str
	 * @param regex
	 * @return
	 */
	public static boolean regexes(String str,String regex){
		if(str == null){
			
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		
		return m.matches();		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(071);//8����
			System.out.println(regexes(new String(new byte[]{(byte)0xFF,(byte)0xAA,(byte)056},"iso-8859-1"),"\\xFF\\xaa\\056"));
			System.out.println(regexes("-2s","-?\\d+"));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
