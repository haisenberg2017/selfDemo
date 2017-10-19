package com.haisenberg.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StrUtil {
	/***
	 * String分割为list 且剔除list中的重复数据
	 *
	 */
	public static List<String> StrtoList(String str, String regex) {
		List<String> list = new ArrayList<String>();
		str = str.trim();
		if (StringUtils.isNotBlank(str)) {

			String[] split = str.split(regex);
			if (split.length > 0) {
				for (String string : split) {
					if (StringUtils.isNotBlank(string)) {
						if (!list.contains(string)) {// 删除list中的重复数据
							string = string.replace("'", "\\'");
							list.add(string.trim());
						}
					}
				}
			}
		}
		return list;
	}

	/***
	 * a/b转比率
	 *
	 */
	public static String floattoRate(int a, int b) {
		String rate = null;
		if (a != 0 && b != 0) {
			float f = Float.valueOf(a) / b;
			BigDecimal bd = new BigDecimal(f * 100);
			float f1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			rate = String.valueOf(f1) + "%";
		} else {
			rate = "0.00%";
		}
		return rate;
	}

	/**
	 * 返回double类型小数点num位的数 小数不进位
	 * 
	 * @param a
	 * @param num
	 * @param roundType =  BigDecimal.(ROUND_UP、ROUND_DOWN、ROUND_CEILING、ROUND_FLOOR、ROUND_HALF_UP、ROUND_HALF_DOWN、ROUND_HALF_EVEN、ROUND_UNNECESSARY);
	 * @return
	 */
	public static double returnDouble(double a, int num,int roundType) {
		String str = String.valueOf(a);
		if (str.length() - str.indexOf(".") - 1 > num) {
			BigDecimal bg = new BigDecimal(a);
			double f1 = bg.setScale(num, roundType).doubleValue();
			return f1;
		} else {
			return a;
		}

	}

	public static void main(String[] args) {
		/*
		 * String[]
		 * colmns={"fadownerid","fad_exposure","fad_hits","fpay_money"};
		 * Object[] newValue={5,null,null,null}; String encryptdata =
		 * EncryptedData.EncryptedAd(colmns, newValue);
		 * System.out.println(encryptdata);
		 */
		
		  double returnDoubleUp = returnDouble(0.023, 2,BigDecimal.ROUND_UP);
		 System.out.println(returnDoubleUp);
		 
	/*	double a = 0;
		String str = String.valueOf(a);
		int indexOf = str.length() - str.indexOf(".") - 1;
		System.out.println(indexOf);
*/
	}

}
