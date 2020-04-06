package com.micro.pmo.commons.utils;

import java.util.Arrays;
import java.util.HashMap;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月22日 
*/
public class EncryptUtil {

	/**
	 * 按照map中的key的首字母的ascii码从小到大生成 key=value&key=value形式的字符串
	 * @param params
	 * @return
	 */
	public static String encryptByInitial(HashMap<String, String> params) {
		// 排序
		String[] keys = params.keySet().toArray(new String[params.keySet().size()]);
		//按首字母排序
		Arrays.sort(keys);
		// 存放解密的参数
		StringBuilder sb = new StringBuilder();
		String paramsKey = null;
		for (int sub = 0, length = keys.length; sub < length; sub++) {
			paramsKey = keys[sub];
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(paramsKey + "=" + params.get(paramsKey));
		}
		return sb.toString();
	}
	
	/** 
	 * 按照map中的key的首字母的ascii码从小到大生成 key=value&key=value形式的字符串 并使用md5加密
	 * @param params
	 * @return
	 */
	public static String encryptByInitialAndMd5(HashMap<String, String> params) {
		return HashKit.md5(encryptByInitial(params));
	}
	
}
