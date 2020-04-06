package com.micro.pmo.commons.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;


public class AESUtil {

	public static final String user_from = "2019HYWZH";
	
	public static final String user_password = "1234567890123456";
	
	public static String encrypt( String input, String key){
		byte[] crypted = null;

		try {
			SecretKeySpec skey = new SecretKeySpec( key.getBytes(), "AES" );

			Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );

			cipher.init( Cipher.ENCRYPT_MODE, skey );

			crypted = cipher.doFinal( input.getBytes() );
		} catch ( Exception e ) {
			System.out.println( e.toString() );
		}

		return(new String( Base64.encodeBase64( crypted ) ) );
	}


	public static String decrypt( String input, String key )
	{
		byte[] output = null;

		try {
			SecretKeySpec skey = new SecretKeySpec( key.getBytes(), "AES" );

			Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );

			cipher.init( Cipher.DECRYPT_MODE, skey );

			output = cipher.doFinal( Base64.decodeBase64( input ) );
		} catch ( Exception e ) {
			System.out.println( e.toString() );
		}

		return(new String( output ) );
	}
    
    /***
     * 内容转换成base64
     * @param content
     * @return
     */
    public static String contentToBase64(String content){
    	try {
    		if(StringUtils.isBlank(content)){
    			return null;
    		}
			content = URLEncoder.encode(content,"UTF-8");
			byte ptext[] = content.getBytes("UTF-8");//将字符串转换成byte类型数组，实质是各个字符的二进制形式
			BigInteger m = new BigInteger(ptext);//二进制串转换为一个大整数
			return String.valueOf(m);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return content;
    }
    /**
     * base64转内容
     * @param base64
     * @return
     */
    public static String base64ToContent(String base64){
    	try {
    		if(StringUtils.isBlank(base64)){
    			return null;
    		}
    		BigInteger m = new BigInteger(base64);
    		byte[] mt = m.toByteArray();//m为密文的BigInteger类型
    		String content = new String(mt,"UTF-8");
    		content = URLDecoder.decode(content,"UTF-8");
    		return content;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return base64;
    }

}
