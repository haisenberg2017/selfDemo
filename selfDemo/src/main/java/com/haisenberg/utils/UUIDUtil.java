package com.haisenberg.utils;

import java.util.UUID;

public class UUIDUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void main(String[] args) {
		String str = UUIDUtil.getUUID();
		System.out.println(str);
	}

}
