package com.haisenberg.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 将外国地区改为未知
 * 
 **/
public class AreaUtil {

	/**
	 * 判断String是否是中国的省
	 * 是则返回本身 否则返回未知
	 */
	public static String CNCheck(String str){
		//记录中国的省
		List<String> cnlist=new ArrayList<String>();
		cnlist.add("北京");
		cnlist.add("天津");
		cnlist.add("河北");
		cnlist.add("山西"); 
		cnlist.add("内蒙古");
		cnlist.add("辽宁"); 
		cnlist.add("吉林"); 
		cnlist.add("黑龙江");
		cnlist.add("上海"); 
		cnlist.add("江苏"); 
		cnlist.add("浙江");
		cnlist.add("安徽");
		cnlist.add("福建"); 
		cnlist.add("江西"); 
		cnlist.add("山东"); 
		cnlist.add("河南");
		cnlist.add("湖北");
		cnlist.add("湖南");
		cnlist.add("广东");
		cnlist.add("广西");
		cnlist.add("海南");
		cnlist.add("重庆");
		cnlist.add("四川"); 
		cnlist.add("贵州"); 
		cnlist.add("云南");
		cnlist.add("西藏");
		cnlist.add("陕西");
		cnlist.add("甘肃");
		cnlist.add("青海");
		cnlist.add("宁夏");
		cnlist.add("新疆");
		cnlist.add("台湾");
		cnlist.add("香港");
		cnlist.add("澳门");
		
		cnlist.add("中国台湾");
		cnlist.add("中国香港");
		cnlist.add("中国澳门");
		
		if(cnlist.contains(str)){
			if("香港".equals(str)){
				return "中国香港";
			}else if("澳门".equals(str)){
				return "中国澳门";
			}else if("台湾".equals(str)){
				return "中国台湾";
			}else{
				return str;
			}	
		}else{
			return "未知";
		}
	}

	public static String CNCity(String city) {
		city = city.trim();
		if (StringUtils.isNotBlank(city)) {
			String[] split = city.split("  ");
			if ("中国".equals(split[0])) {
				if (split.length == 2) {// city="中国 xx"
					city = CNCheck(split[1]);
				} else {// city="中国"
					city = "未知";
				}
			} else {
				if (split.length == 2) {// city="xx xx"
					if ("未知".equals(CNCheck(split[0]))) {// city="非洲 xx"
						city = "未知";
					} else {// city="广州 xx"
						city = CNCheck(split[0]) + "  " + split[1];
					}
				} else {// city="广州 "
					city = CNCheck(split[0]);
				}
			}
		} else {
			city = "未知";
		}
		return city;
	}
}
