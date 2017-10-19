package com.haisenberg.utils;

import java.math.BigDecimal;

public class MathUtil {
	/**
	 * 提供double精确加法计算的sum方法
	 * 
	 * @param value1 被加数
	 * @param value2 加数
	 * @return 两个参数的和
	 */
	public static double sumForDouble(double value1, double value2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(value1));
		BigDecimal bd2 = new BigDecimal(Double.toString(value2));
		return bd1.add(bd2).doubleValue();
	}

	/**
	 * 提供double精确减法运算的sub方法
	 * 
	 * @param value1  被减数
	 * @param value2  减数
	 * @return 两个参数的差
	 */
	public static double subForDouble(double value1, double value2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(value1));
		BigDecimal bd2 = new BigDecimal(Double.toString(value2));
		return bd1.subtract(bd2).doubleValue();
	}
	
	/**
	 * 提供double精确乘法运算的multiply方法
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static double multiplyForDouble(double value1, double value2) {
	BigDecimal bd1 = new BigDecimal(Double.toString(value1));
	BigDecimal bd2 = new BigDecimal(Double.toString(value2));
	return bd1.multiply(bd2).doubleValue();
	}
	
	
	public static void main(String[] args) {
/*		double sumForDouble = sumForDouble(10, 0);
		double subForDouble = subForDouble(10.33, 9.16);
		double multiplyForDouble = multiplyForDouble(10+1, 9.16);
		System.out.println(sumForDouble + "," + subForDouble+","+multiplyForDouble);*/
	BigDecimal bd1 = new BigDecimal(0.2);
		BigDecimal bd2 = new BigDecimal(0.4);
		BigDecimal add = bd1.add(bd2);
		System.out.println(add.setScale(2, BigDecimal.ROUND_DOWN));
	}
}
