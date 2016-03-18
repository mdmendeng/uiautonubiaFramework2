package com.nubia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.R.integer;
import android.os.Bundle;
import android.os.RemoteException;

import com.android.uiautomator.core.Configurator;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.nubia.uiautotest.device.Click;
import com.nubia.uiautotest.device.Gestures;
import com.nubia.uiautotest.locators.ElementLocator;
import com.nubia.uiautotest.rules.AssertCollector;
import com.nubia.uiautotest.utils.CommonUtil;
import com.nubia.uiautotest.utils.FileUtil;
import com.nubia.uiautotest.utils.PackageUtil;
import com.nubia.uiautotest.utils.ScreenShotUtil;
import com.nubia.uiautotest.utils.Shell;
import com.nubia.uiautotest.utils.SystemUtil;
import com.nubia.uiautotest.watcher.Watcher;

public class testEasyFramework_old extends UiAutomatorTestCase {
	// 测试准备
	PackageUtil packageUtil = new PackageUtil();
	CommonUtil commonUtil = new CommonUtil();
	
	protected void setUp() throws Exception {
		Configurator.getInstance().setKeyInjectionDelay(200);
		FileUtil.creatFile(getClass().getName());
		Gestures.unLock(getUiDevice());
		Watcher.watcherTheError(getUiDevice(), getClass().getName());// 运行错误报告监听器
		Watcher.watchersogou(getUiDevice(), getClass().getName());// 运行搜狗弹框监听器
		Watcher.watcherThePhone(getUiDevice(), getClass().getName());// 运行电话监听器
	}

	public void testDemo() throws UiObjectNotFoundException, RemoteException,
			android.os.RemoteException, IOException {
		try {
			Bundle bundle = getParams();
			String s = bundle.getString("runInfo1");
			System.out.println("用例步骤为" + s);
			if(s.contains("test")){
			deletePictures(s);}
		} catch (UiObjectNotFoundException e) {
			ScreenShotUtil.takeshot(getUiDevice(), getClass().getName());
			throw e;
		} finally {
//			getUiDevice().pressHome();
		}
	}

	public void deletePictures(String s) throws UiObjectNotFoundException,
		RemoteException, IOException {
		//int runTimeCount = 0;
//		if(s.split("%%step%%")[1].split("%%")[0].equals("testcount")){//测试循环次数,由于测试次数使用外部控制，这里就注释掉
//			runTimeCount=Integer.parseInt(s.split("%%step%%")[1].split("%%")[3]);
//		}
//		for(int times = 0;times<runTimeCount;times++){
		
		//测试读取文件
		String encoding = "GBK";
		File file = new File("/sdcard/2.txt");
		if (file.isFile() && file.exists()) { // 判断文件是否存在
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				System.out.println(lineTxt);
			getUiDevice().pressHome();
			ElementLocator.textContainsObject(lineTxt).click();
			}
			read.close();
		} else {
			System.out.println("找不到指定的文件");
		}
		
		
		
		for(int i=0;i<s.split("%%step%%").length-1;){
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("click")){//操作是点击
				if(s.split("%%step%%")[i+1].split("%%")[0].equals("text")){//文本
					ElementLocator.textObject(s.split("%%step%%")[i+1].split("%%")[1]).click();}
				if(s.split("%%step%%")[i+1].split("%%")[0].equals("resource-id")){//文本
					ElementLocator.resourceIdObject(s.split("%%step%%")[i+1].split("%%")[1]).click();}
				if(s.split("%%step%%")[i+1].split("%%")[0].equals("class")){//类名
					ElementLocator.classNameObject(s.split("%%step%%")[i+1].split("%%")[1]).click();}
				if(s.split("%%step%%")[i+1].split("%%")[0].equals("xy")){// 坐标点
					getUiDevice().click(
							Integer.parseInt(s.split("%%step%%")[i + 1].split("%%")[1]
									.split("x")[0]),
							Integer.parseInt(s.split("%%step%%")[i + 1].split("%%")[1]
									.split("x")[1]));
				}
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("sleep")){//操作是休眠
				sleep(Integer.parseInt(s.split("%%step%%")[i+1].split("%%")[3])*1000);
			}
			
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("home")){//操作是home
				getUiDevice().pressHome();
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("menu")){//操作是menu
				getUiDevice().pressMenu();
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("back")){//操作是back
				getUiDevice().pressBack();
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("screenshot")){//步骤截图
				ScreenShotUtil.takeshot(getUiDevice(),getClass().getName(), "the_"+Integer.toString(i)+"_step");
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("check")){
				AssertCollector
						.assertEqual(
								s.split("%%step%%")[i + 1].split("%%")[3]
										.split(",")[0],
								ElementLocator
										.textObject(
												s.split("%%step%%")[i + 1]
														.split("%%")[1])
										.getText(), getClass().getName(),
								getUiDevice(), s.split("%%step%%")[i + 1]
										.split("%%")[3].split(",")[1]);//断言检查，等于
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("openActivity")){
				SystemUtil.cmdImput(packageUtil.getSettings());//没有找到好些的办法
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("openBrowser")){//打开浏览器
				Shell.openUrl(s.split("%%step%%")[i + 1].split("%%")[3]);
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("inputMethonSwitch")){//输入法切换到英语
				commonUtil.inputMethonSwitch(getUiDevice());
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("dataPicker")){//滚轮选择器
				Gestures.dataPicker(ElementLocator.resourceIdObject(s.split("%%step%%")[i+1].split("%%")[1]), 1, 1);//测试通过后增加其他定位方法
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("longclick")){//长按
				Click.longClick(getUiDevice(), ElementLocator.resourceIdObject(s.split("%%step%%")[i+1].split("%%")[1]), 200);
			}
			if(s.split("%%step%%")[i+1].split("%%")[2].equals("keycode")){//输入字符
				getUiDevice().pressKeyCode(Integer.parseInt(s.split("%%step%%")[i+1].split("%%")[3]));
			}
			if (s.split("%%step%%")[i + 1].split("%%")[2].equals("unlock")) {//解锁
				Gestures.unLock(getUiDevice());
			}
//			else if(s.split("%%step%%")[i + 1].split("%%")[3]){
//				System.out.println("当前操作没有参数");
//			}
			else{
				System.out.println("没有任何输入");
			}
			i++;	
		}
		}
//	}
}
