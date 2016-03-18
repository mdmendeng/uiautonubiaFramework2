package com.nubia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import android.os.RemoteException;

import com.android.uiautomator.core.Configurator;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
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

public class testEasyFramework extends UiAutomatorTestCase {
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
			runTestCase();
		} catch (UiObjectNotFoundException e) {
			ScreenShotUtil.takeshot(getUiDevice(), getClass().getName());
			throw e;
		} finally {
//			getUiDevice().pressHome();
		}
	}

	public void runTestCase() throws UiObjectNotFoundException,
		RemoteException, IOException {
		//测试读取文件
		String encoding = "GBK";
		File file = new File("/sdcard/testSteps.txt");
		int testCount = 1;
		 List <String>lst=new ArrayList<String>();
		if (file.isFile() && file.exists()) {// 判断文件是否存在
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine())!= null) {
				if(lineTxt.contains("testcount")){//循环次数
					testCount = Integer.parseInt(lineTxt.split("count")[1]);
					System.out.println(lineTxt+"和"+testCount);
				}
				else if(lineTxt.contains("step")){
					lst.add(lineTxt);
				}
			}
			
			for(int i = 0;i<testCount;i++){//循环测试次数，默认为1
				for(int j=0;j<lst.size();j++){//每一行进行测试
				System.out.println("testcase is "+lst.get(j));
				runTest(lst.get(j));
				}
			}
			read.close();
		} else {
			System.out.println("找不到指定的文件");
			}
		}
	
	public void runTest(String s) throws UiObjectNotFoundException, RemoteException{
			
			if(s.contains("testend")){
				System.out.println("测试结束");
			}
			else if(s.contains("testcount")){
				System.out.println("测试循环次数"+ Integer.parseInt(s.split("count")[1]));
			}
			else if(s.split("%%")[3].equals("click")){//操作是点击
				if(uiObject(s)!=null){
					System.out.println("不为空"+s);
					uiObject(s).click();
				}
			}
			else if(s.split("%%")[3].equals("sleep")){//操作是休眠
				sleep(Integer.parseInt(s.split("%%")[4])*1000);
			}
			else if(s.split("%%")[3].equals("home")){//操作是home
				getUiDevice().pressHome();
			}
			else if(s.split("%%")[3].equals("menu")){//操作是menu
				getUiDevice().pressMenu();
			}
			else if(s.split("%%")[3].equals("back")){//操作是back
				getUiDevice().pressBack();
			}
			else if(s.split("%%")[3].equals("screenshot")){//步骤截图
			ScreenShotUtil.takeshot(getUiDevice(), getClass().getName(), "the_"
					+ s.split("%%")[0].replace("	", "_").split("step")[0]+ "_step");//步骤
			}
			else if (s.split("%%")[3].equals("check")) {
				assertCollector(s);// 断言检查，等于
			}
			else if(s.split("%%")[3].equals("openActivity")){
				SystemUtil.cmdImput("am start -n "+s.split("%%")[4]);//输入activity打开
			}
			else if(s.split("%%")[3].equals("openBrowser")){//打开浏览器url
				Shell.openUrl(s.split("%%")[4]);
			}
			else if(s.split("%%")[3].equals("inputMethonSwitch")){//输入法切换到英语
				commonUtil.inputMethonSwitch(getUiDevice());
			}
			else if(s.split("%%")[3].equals("dataPicker")){//滚轮选择器
			Gestures.dataPicker(uiObject(s),
					Integer.parseInt(s.split("%%")[4].split(",")[0]),
					Integer.parseInt(s.split("%%")[4].split(",")[1]));// 滑动格数，方向
			}
			else if(s.split("%%")[3].equals("longclick")){//长按，需要增加其他
				Click.longClick(getUiDevice(), uiObject(s), 200);
			}
			else if(s.split("%%")[3].equals("keycode")){//输入字符
				getUiDevice().pressKeyCode(Integer.parseInt(s.split("%%")[4]));
			}
			else if (s.split("%%")[3].equals("unlock")) {//解锁
				Gestures.unLock(getUiDevice());
			}
//			else if(s.split("%%step%%")[i + 1].split("%%")[3]){
//				System.out.println("当前操作没有参数");
//			}
			else{
				System.out.println("没有任何输入");
			}
	}
	
	public UiObject uiObject(String s1){//处理返回对象
		if(s1.split("%%")[1].equals("text")){//文本
			return ElementLocator.textObject(s1.split("%%")[2]);}
		else if(s1.split("%%")[1].equals("resource-id")){//ID
			return ElementLocator.resourceIdObject(s1.split("%%")[2]);}
		else if(s1.split("%%")[1].equals("class")){//类名
			return ElementLocator.classNameObject(s1.split("%%")[2]);}
		else if(s1.split("%%")[1].equals("textContains")){//文本包括
			return ElementLocator.textContainsObject(s1.split("%%")[2]);}
		else if(s1.split("%%")[1].equals("xy")){// 坐标点不返回对象
			getUiDevice().click(
					Integer.parseInt(s1.split("%%")[2]
							.split("x")[0]),
					Integer.parseInt(s1.split("%%")[2]
							.split("x")[1]));
			return null;
		}
		else{
		return null;}
	}
	
	public void assertCollector(String s2) throws UiObjectNotFoundException{//只支持文本比较
		if(s2.split("%%")[4].split(",")[0].equals("包含")){
			AssertCollector.assertContains(s2.split("%%")[4].split(",")[1],
						uiObject(s2).getText(), getClass().getName(),
						getUiDevice(), s2.split("%%")[4].split(",")[2]);
		}
		else if(s2.split("%%")[4].split(",")[0].equals("字符串等于")){
			AssertCollector.assertEqual(s2.split("%%")[4].split(",")[1],
						uiObject(s2).getText(), getClass().getName(),
						getUiDevice(), s2.split("%%")[4].split(",")[2]);
		}
		else if(s2.split("%%")[4].split(",")[0].equals("反向包含")){
			AssertCollector.assertBackContains(s2.split("%%")[4].split(",")[1],
						uiObject(s2).getText(), getClass().getName(),
						getUiDevice(), s2.split("%%")[4].split(",")[2]);
		}
		else if(s2.split("%%")[4].split(",")[0].equals("数字等于")){
			AssertCollector.assertEqual(Integer.parseInt(s2.split("%%")[4].split(",")[1]),
					Integer.parseInt(uiObject(s2).getText()), getClass().getName(),
						getUiDevice(), s2.split("%%")[4].split(",")[2]);
		}
		else if(s2.split("%%")[4].split(",")[0].equals("正则匹配")){
			AssertCollector.assertMatch(s2.split("%%")[4].split(",")[1],
					uiObject(s2).getText(), getClass().getName(),
						getUiDevice(), s2.split("%%")[4].split(",")[2]);
		}
		else{
			System.out.println("输入检查点错误，请重新输入");
		}
		
	}
	
}
