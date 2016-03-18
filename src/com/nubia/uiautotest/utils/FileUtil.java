package com.nubia.uiautotest.utils;

import java.io.File;

import com.android.uiautomator.core.Configurator;

public class FileUtil {
	/**
	 * 创建截图文件夹方法
	 * @param string
	 */
	public static void creatFile(String classname){
		Configurator.getInstance().setKeyInjectionDelay(200);//作为前提条件调整点击间200ms时延
		File file = new File("/storage/sdcard0/uiauto/"+classname.split("\\.")[classname.split("\\.").length-1]);
		file.mkdirs();//创建目录
	}
}
