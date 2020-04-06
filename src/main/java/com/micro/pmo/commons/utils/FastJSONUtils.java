package com.micro.pmo.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public final class FastJSONUtils {

	/**
	 * 解析Long数组
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static List<String> parseStringList(JSONObject obj, String fieldName) {
		List<String> list = new ArrayList<String>();
		if (obj == null || StringUtils.isEmpty(fieldName)) {
			return list;
		}
		JSONArray array = obj.getJSONArray(fieldName);
		if (CollectionUtils.isEmpty(array)) {
			return list;
		}
		for (int j = 0, jsize = array.size(); j < jsize; j++) {
			String id = array.getString(j);
			if (id != null) {
				list.add(id);
			}
		}
		return list;
	}

	public static Map<String, String> toMap(JSONObject obj) {
		if (obj == null) {
			return null;
		}
		JSONObject jsonObject = (JSONObject) JSON.toJSON(obj);
		if (jsonObject == null) {
			return null;
		}
		int size = jsonObject.size();
		Map<String, String> map = new HashMap<String, String>(size);
		Set<String> keySet = jsonObject.keySet();
		for (String key : keySet) {
			map.put(key, jsonObject.getString(key));
		}
		return map;
	}
	
}
