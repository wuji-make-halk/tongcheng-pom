package com.micro.pmo.commons.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月19日
 */
public class Kv<K, V> extends HashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Kv() {
	}

	public static <K, V> Kv<K, V> by(K key, V value) {
		return new Kv<K, V>().set(key, value);
	}

	public static <K, V> Kv<K, V> create() {
		return new Kv<K, V>();
	}

	public Kv<K, V> set(K key, V value) {
		super.put(key, value);
		return (Kv<K, V>) this;
	}

	public Kv<K, V> setIfNotBlank(K key, V value) {
		if (value != null) {
			this.set(key, value);
		}
		return this;
	}

	public Kv<K, V> setIfNotNull(K key, V value) {
		if (value != null) {
			set(key, value);
		}
		return this;
	}

	public Kv<K, V> set(Map<K, V> map) {
		super.putAll(map);
		return this;
	}

	public Kv<K, V> set(Kv<K, V> kv) {
		super.putAll(kv);
		return this;
	}

	public Kv<K, V> delete(K key) {
		super.remove(key);
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T>T getAs(K key) {
		return  (T) get(key);
	}

	public String getStr(K key) {
		Object s = get(key);
		return s != null ? s.toString() : null;
	}
	
	public BigDecimal getBigDecimal(K key) {
		Object n = get(key);

		if (n == null) {
			return null;
		}

		BigDecimal i = null;
		if (n instanceof BigDecimal) {
			i = (BigDecimal) n;
		} else if(n instanceof Integer) {
			i = new BigDecimal(getInt(key));
		}else if (n instanceof String) {
			try {
				i = new BigDecimal(n.toString());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return i != null ? i : null;
	} 

	public Integer getInt(K key) {
		Object n = get(key);

		if (n == null) {
			return null;
		}

		Integer i = null;
		if (n instanceof Integer) {
			i = (Integer) n;
		} else if (n instanceof String) {
			try {
				i = Integer.parseInt(n.toString());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return i != null ? i : null;
	}

	public Long getLong(K key) {
		Number n = (Number) get(key);
		return n != null ? n.longValue() : null;
	}

	public Number getNumber(K key) {
		return (Number) get(key);
	}

	public Boolean getBoolean(K key) {
		return (Boolean) get(key);
	}

	/**
	 * key 存在，并且 value 不为 null
	 */
	public boolean notNull(Object key) {
		return get(key) != null;
	}

	/**
	 * key 不存在，或者 key 存在但 value 为null
	 */
	public boolean isNull(Object key) {
		return get(key) == null;
	}

	/**
	 * key 存在，并且 value 为 true，则返回 true
	 */
	public boolean isTrue(Object key) {
		Object value = get(key);
		return (value instanceof Boolean && ((Boolean) value == true));
	}

	/**
	 * key 存在，并且 value 为 false，则返回 true
	 */
	public boolean isFalse(Object key) {
		Object value = get(key);
		return (value instanceof Boolean && ((Boolean) value == false));
	}

	public String toJson() {
		return JSONObject.toJSONString(this);
	}

	public boolean equals(Object kv) {
		return kv instanceof Kv && super.equals(kv);
	}
}
