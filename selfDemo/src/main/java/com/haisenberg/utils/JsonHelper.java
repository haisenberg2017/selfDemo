package com.haisenberg.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

public class JsonHelper {
	/**
	 * 
	 * map转换json.
	 * 
	 * @param map
	 *            集合
	 */
	public static String mapToJson(Map<String, Object> map) {
		Set<String> keys = map.keySet();
		String key = "";
		String value = "";
		StringBuffer jsonBuffer = new StringBuffer();
		jsonBuffer.append("{");
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			key = (String) it.next();
			value = map.get(key).toString();
			jsonBuffer.append(key + ":" + "\"" + value + "\"");
			if (it.hasNext()) {
				jsonBuffer.append(",");
			}
		}
		jsonBuffer.append("}");
		return jsonBuffer.toString();
	}

	/**
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 *
	 * @param jsonString
	 * @return
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map jsonToMap(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator keyIter = jsonObject.keys();
		String key;
		Object value;
		Map valueMap = new HashMap();
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key).toString();
			valueMap.put(key, value);
		}
		return valueMap;
	}

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象
	 * 
	 * @param jsonString
	 * @param beanCalss
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T bean = (T) JSONObject.toBean(jsonObject, beanCalss);

		return bean;

	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param bean
	 * @return
	 */
	public static String beanToJson(Object bean) {

		JSONObject json = JSONObject.fromObject(bean);

		return json.toString();

	}

}
