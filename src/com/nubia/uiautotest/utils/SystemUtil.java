package com.nubia.uiautotest.utils;

import java.io.IOException;

public class SystemUtil {
	/**
	 * Runtime指令方法
	 * @param commond
	 */
	public static void cmdImput(String commond){
		try {
			Runtime.getRuntime().exec(commond);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
