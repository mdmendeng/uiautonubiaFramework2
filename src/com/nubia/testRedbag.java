package com.nubia;

import com.android.uiautomator.core.Configurator;
import com.android.uiautomator.core.UiDevice;
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

import android.bluetooth.BluetoothClass.Device;
import android.os.RemoteException;



public class testRedbag extends UiAutomatorTestCase {
	// 测试准备
	PackageUtil packageUtil = new PackageUtil();
	CommonUtil commonUtil = new CommonUtil();
	UiDevice device = getUiDevice();
	
	protected void setUp() throws Exception {
		//Configurator.getInstance().setKeyInjectionDelay(200);
		System.out.println("ssssss");
		FileUtil.creatFile(getClass().getName());
		Gestures.unLock(getUiDevice());
		Watcher.watcherTheError(getUiDevice(), getClass().getName());//运行错误报告监听器
		Watcher.watchersogou(getUiDevice(), getClass().getName());//运行搜狗弹框监听器
		Watcher.watcherThePhone(getUiDevice(), getClass().getName());//运行电话监听器
	}

	public void testDemo() throws UiObjectNotFoundException, RemoteException, android.os.RemoteException {
		try {
			testbg();
		} catch (UiObjectNotFoundException e) {
//			ScreenShotUtil.takeshot(getUiDevice(), getClass().getName());
			throw e;
		} finally {
//			getUiDevice().pressHome();
		}
	}

	public void testbg() throws UiObjectNotFoundException, RemoteException {
		System.out.println("ssssss");
		device.pressHome();
		String wx = "am start -n com\\.tencent.mm/.ui\\.LauncherUI";
		SystemUtil.cmdImput(wx);
		System.out.println("sssss");
		if(ElementLocator.resourceIdObject("com.tencent.mm:id/du").exists()){//在wx界面
			ElementLocator.textObject("潘亚男").click();
			System.out.println("ssss");
		}
		if(ElementLocator.resourceIdObject("com.tencent.mm:id/u9").exists()){//聊天界面
//			UiCollection  rbs= new UiCollection(new UiSelector().resourceId("com.tencent.mm:id/wk"));
//			rbs.
			for(int i = 0;i<10000;i++){
				if(ElementLocator.textObject("微信红包").exists()){
					System.out.println("ss");
					ElementLocator.textObject("微信红包").click();
					ElementLocator.resourceIdObject("com.tencent.mm:id/aww").waitForExists(5000);
					if(ElementLocator.resourceIdObject("com.tencent.mm:id/aww").exists()){
						ElementLocator.resourceIdObject("com.tencent.mm:id/aww").click();
						sleep(1000);
						getUiDevice().pressBack();
					}
					else{
						getUiDevice().pressBack();
					}
				}
				else{
					sleep(100);
				}
			}
		}
		else{
			System.out.println("没有这个包");
		}
	}
}
