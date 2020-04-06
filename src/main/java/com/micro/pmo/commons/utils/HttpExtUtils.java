package com.micro.pmo.commons.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;

/**
 * 
 * @类功能说明：HTTP扩展工具
 * 
 * @作者：Liu.Wen
 * @创建时间：2017年6月22日 下午1:11:08
 * @版本：V1.0
 */
public class HttpExtUtils {

	public static final String toUrlFormData(Map<String, String> map) {
		if (MapUtils.isEmpty(map)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			sb.append(entry.getKey()).append("=").append(entry.getValue());
			if (it.hasNext()) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

}
